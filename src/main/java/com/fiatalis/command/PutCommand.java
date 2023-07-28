package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.modelMessage.FileMessage;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.File;
import java.io.IOException;

public class PutCommand extends CommandsRun {
    public PutCommand(Attribute attribute) {
        super(attribute);
    }

    boolean isDelete = false;

    @Override
    public void help() {
        Utils.printConsole("Загружает файл на сервера");
    }

    @Override
    void run() {
        try {
            if (attribute.getOptions().equals("-d")) {isDelete = true;}
        }catch (NullPointerException e){}
        if (Client.getInstance().getClientDir().resolve(attribute.getAttribute()).toFile().isDirectory() && checkFile(attribute.getAttribute())) {
            System.out.println("Попа папку передать не могу, но скоро возможность появится.");
        } else if (checkFile(attribute.getAttribute())) {
            String item = attribute.getAttribute();
            File selected = Client.getInstance().getClientDir().resolve(item).toFile();
            try {
                Client.getInstance().getOos().writeObject(new FileMessage(selected.toPath()));
            } catch (IOException e) {
                System.out.println("Не удалось отправить файл: " + item);
            }
            deleteFile(selected, item);
            Client.getInstance().updateServerViewPath();
        } else {
            System.out.println("!");
        }



        if (checkFile(attribute.getAttribute())) {
            try {
                Client.getInstance().getOos().writeObject(new FileRequest(attribute.getAttribute(), isDelete));
            } catch (IOException e) {
                System.out.println("Не удалось скачать файл, ошибка.");
            }
        } else {
            System.out.println("Имя файла введено не корректно!");
        }
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        } else {
            run();
        }
    }

    private Boolean checkFile(String fileName) {
        return Client.getInstance().getServerView().stream().anyMatch(s -> s.equals(fileName));
    }

    private void deleteFile(File file, String name) {
        if (isDelete) {
            if (file.delete()) {
                System.out.println("File: " + name + " delete");
            }
        }
    }
}
