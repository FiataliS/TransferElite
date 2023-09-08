package com.fiatalis.server;

import com.fiatalis.entity.Language;
import com.fiatalis.entity.Server;
import com.fiatalis.entity.Skin;
import com.fiatalis.server.handler.CloudMessageHandler;
import com.fiatalis.utils.Utils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedDeque;

public class EchoServer {
    private EventLoopGroup auth;
    private EventLoopGroup worker;
    private ConcurrentLinkedDeque<ChannelHandlerContext> users;
    private ChannelFuture channelFuture;
    private Thread thread;
    private boolean skin = Skin.getInstance().getSkin();
    private ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());

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
            if (!skin) {
                Utils.printConsole(rb.getString("serverWorker") , true);
            }
        }
    }

    public void stopServer() {
        if (thread.isAlive()) {
            thread.interrupt();
            instance = new EchoServer();
        } else {
            if (!skin) {
                Utils.printConsole(rb.getString("writeStart") , true);
            }
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
                            socketChannel.config().setReceiveBufferSize(1024*1024*1024);
                            socketChannel.config().setSendBufferSize(1024*1024*1024);
                            socketChannel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new HttpObjectAggregator(Integer.MAX_VALUE),
                                    new CloudMessageHandler()
                            );
                        }
                    });
            channelFuture = bootstrap.bind(Integer.parseInt(Server.getInstance().getPort())).sync();
            if (!skin) {
                Utils.printConsole(rb.getString("serverStarted") , true);
            }
            Utils.addPrefix();
            channelFuture.channel().closeFuture().sync(); // block
        } catch (InterruptedException e) {
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
            if (!skin) {
                Utils.printConsole(rb.getString("serverStopped") , true);
            }
            channelFuture.channel().disconnect();
        }
    }
}
