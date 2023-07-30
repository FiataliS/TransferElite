package com.fiatalis.client.command;

import com.fiatalis.client.Client;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.IOException;

public class Get extends CommandRun {
    boolean isDelete = false;

    public Get(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Загружает файл с сервера");
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.DELETE) {
            isDelete = true;
        }
        get();
    }

    private void get() {
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

    private Boolean checkFile(String fileName) {
        return Client.getInstance().getServerView().stream().anyMatch(s -> s.equals(fileName));
    }
}
