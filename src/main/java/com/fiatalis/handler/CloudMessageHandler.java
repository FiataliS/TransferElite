package com.fiatalis.handler;


import com.fiatalis.entity.Directory;
import com.fiatalis.modelMessage.CloudMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiConsumer;

import static com.fiatalis.handler.HandlerOperation.HANDLER_MAP;

public class CloudMessageHandler extends SimpleChannelInboundHandler<CloudMessage> {

    private static Path serverDir = Path.of(Directory.getInstance().getName());

    public static Path getServerDir() {
        return serverDir;
    }

    public static void setServerDir(Path serverDir) {
        CloudMessageHandler.serverDir = serverDir;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        serverDir = Paths.get(Directory.getInstance().getName());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CloudMessage cloudMessage) throws Exception {
        BiConsumer<ChannelHandlerContext, CloudMessage> channelHandlerContextCloudMessageBiConsumer =
              HANDLER_MAP.get(cloudMessage.getMessageType());
        channelHandlerContextCloudMessageBiConsumer.accept(ctx, cloudMessage);
    }

}
