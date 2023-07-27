package com.fiatalis;

import com.fiatalis.entity.ServerAddress;
import com.fiatalis.handler.CloudMessageHandler;
import com.fiatalis.utils.Utils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ConcurrentLinkedDeque;

public class EchoServer {
    private EventLoopGroup auth;
    private EventLoopGroup worker;
    private ConcurrentLinkedDeque<ChannelHandlerContext> users;
    private ChannelFuture channelFuture;
    private Thread thread = null;

    private static volatile EchoServer instance;

    public static EchoServer getInstance() {
        EchoServer localInstance = instance;
        if (localInstance == null) {
            synchronized (EchoServer.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EchoServer();
                }
            }
        }
        return localInstance;
    }

    public EchoServer() {
        auth = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();
        users = new ConcurrentLinkedDeque<>();
        thread = new Thread(this::run);
        thread.setDaemon(true);

    }

    public void startServer() {
        if (!thread.isAlive()) {
            thread.start();
        } else {
            System.out.println("Already working");
        }
    }

    public void stopServer() {
        if (thread.isAlive()) {
            thread.interrupt();
            instance = new EchoServer();
        } else {
            System.out.println("Please enter start");
        }
    }


    public void run() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new CloudMessageHandler()
                            );
                        }
                    });
            channelFuture = bootstrap.bind(Integer.parseInt(new ServerAddress().getEntity().getObjectValue()[1])).sync();
            System.out.println("Server started...");
            Utils.addPrefix();
            channelFuture.channel().closeFuture().sync(); // block
        } catch (InterruptedException e) {
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
            System.out.println("Server stopped...");
            channelFuture.channel().disconnect();
        }
    }
}
