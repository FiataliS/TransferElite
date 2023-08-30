package com.fiatalis.client.command;

import com.fiatalis.client.Client;
import com.fiatalis.modelMessage.FileMessage;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.File;
import java.io.IOException;

public class Put extends CommandRun {
    public Put(Attribute attribute) {
        super(attribute);
    }

    boolean isDelete = false;

    private void put() {
        if (Client.getInstance().getClientDir().resolve(attribute.getAttribute()).toFile().isDirectory() && checkFile(attribute.getAttribute())) {
            System.out.println("Пока папку передать не могу, но скоро возможность появится.");
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
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.DELETE) isDelete = true;
        put();
    }

    private Boolean checkFile(String fileName) {
        for (String s : Client.getInstance().updateClientViewPath()) {
            if (s.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private void deleteFile(File file, String name) {
        if (isDelete) {
            if (file.delete()) {
                System.out.println("File: " + name + " delete");
            }
        }
    }
}
