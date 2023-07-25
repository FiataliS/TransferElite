package com.fiatalis;

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

public class EchoServer extends Thread  {
    private EventLoopGroup auth;
    private EventLoopGroup worker;
    private ConcurrentLinkedDeque<ChannelHandlerContext> users;
    private ChannelFuture channelFuture;

    {
        auth = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();
        users = new ConcurrentLinkedDeque<>();
    }

    @Override
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
            channelFuture = bootstrap.bind(8189).sync();
            System.out.println("Server started...");
            Utils.addPrefix();
            channelFuture.channel().closeFuture().sync(); // block
        } catch (InterruptedException e) {
            // e.printStackTrace();
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
            System.out.println("Server stopped...");
            Utils.addPrefix();
            channelFuture.channel().disconnect();
        }
    }
}
