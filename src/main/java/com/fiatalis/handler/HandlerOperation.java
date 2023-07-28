package com.fiatalis.handler;

import com.fiatalis.modelMessage.*;
import com.fiatalis.utils.AuthServiceUtils;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.fiatalis.modelMessage.MessageType.*;

public class HandlerOperation {

    public static final Map<MessageType, BiConsumer<ChannelHandlerContext, CloudMessage>> HANDLER_MAP = new HashMap<>();

    static {
        HANDLER_MAP.put(FILE, (ctx, cloudMessage) -> {
            try {
                FileMessage fm = (FileMessage) cloudMessage;
                Files.write(CloudMessageHandler.getServerDir().resolve(fm.getName()), fm.getBytes());
                ctx.writeAndFlush(new ListMessage(CloudMessageHandler.getServerDir()));
            } catch (IOException e) {
                System.out.println("Server: ошибка чтения файла.");
            }
        });

        HANDLER_MAP.put(FILE_REQUEST, (ctx, cloudMessage) -> {
            try {
                FileRequest fr = (FileRequest) cloudMessage;
                ctx.write(new FileMessage(CloudMessageHandler.getServerDir().resolve(fr.getName())));
                if (fr.isDelete()) {
                    deleteFile(CloudMessageHandler.getServerDir().resolve(fr.getName()).toFile(), fr.getName(), fr.isDelete());
                }
                ctx.writeAndFlush(new ListMessage(CloudMessageHandler.getServerDir()));
            } catch (IOException e) {
                System.out.println("Server: ошибка запроса на получение файла");
            }
        });

        HANDLER_MAP.put(LIST, (ctx, cloudMessage) -> {
            try {
                ctx.writeAndFlush(new ListMessage(CloudMessageHandler.getServerDir()));
            } catch (IOException e) {
                System.out.println("Server: ошибка отправки списка директории");
            }
        });

        HANDLER_MAP.put(FILE_DIR, (ctx, cloudMessage) -> {
            try {
                FileDir fd = (FileDir) cloudMessage;
                Path fileDir = CloudMessageHandler.getServerDir();
                String item = fd.getItem();
                if (item.equals("...")) {
                    fileDir = fileDir.resolve("..").normalize();
                } else {
                    File selected = fileDir.resolve(item).toFile();
                    if (selected.isDirectory()) {
                        fileDir = fileDir.resolve(item).normalize();
                    }
                }
                CloudMessageHandler.setServerDir(fileDir);
                ctx.writeAndFlush(new ListMessage(CloudMessageHandler.getServerDir()));
            } catch (IOException e) {
                System.out.println("Server: ошибка смены директории.");
            }
        });

        HANDLER_MAP.put(AUTH_SERV, (ctx, cloudMessage) -> {
            try {
                AuthServ authServ = (AuthServ) cloudMessage;
                if (AuthServiceUtils.checkUser(authServ.getName(), authServ.getPass())) {
                    //CloudMessageHandler.setServerDir(CloudMessageHandler.getServerDir().resolve(id).toFile().toPath());
                    ctx.writeAndFlush(new AuthServ(authServ.getName(), "___", true));
                } else {
                    ctx.writeAndFlush(new AuthServ(authServ.getName(), "___", false));
                }
            } catch (IOException e) {
                System.out.println("Server: ошибка аутентификации");
            }
        });

        HANDLER_MAP.put(NEW_FOLDER, (ctx, cloudMessage) -> {
            NewFolder newFolder = (NewFolder) cloudMessage;
            Path path = CloudMessageHandler.getServerDir().resolve(newFolder.getItem());
            try {

                Files.createDirectory(path);
                ctx.writeAndFlush(new ListMessage(CloudMessageHandler.getServerDir()));
            } catch (IOException e) {
                System.out.println("Server: ошибка создания папки.");
            }
        });


    }

    private static void deleteFile(File file, String name, boolean delete) {
        if (delete) {
            if (file.delete()) {
                System.out.println("File: " + name + " delete");
            }
        }
    }
}
