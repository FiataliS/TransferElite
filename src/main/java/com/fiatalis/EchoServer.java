package com.fiatalis;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ConcurrentLinkedDeque;

public class EchoServer implements Runnable {
    EventLoopGroup auth;
    EventLoopGroup worker = new NioEventLoopGroup();
    ConcurrentLinkedDeque<ChannelHandlerContext> users;


    private static EchoServer instance;

    @Override
    public void run() {
        instance = new EchoServer();
        startServer();
    }

    public EchoServer() {
    }

    public static EchoServer getInstance() {
        if (instance == null) {
            instance = new EchoServer();
        }
        return instance;
    }

    public void startServer() {
        auth = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();
        users = new ConcurrentLinkedDeque<>();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null))
                                    // new CloudMessageHandler()
                            );
                        }
                    });
            ChannelFuture future = bootstrap.bind(8189).sync();
            System.out.println("Server started...");
            future.channel().closeFuture().sync(); // block
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public void stopServer() {
        auth.shutdownGracefully();
        worker.shutdownGracefully();
        System.out.println("Server stopped...");
    }
}
