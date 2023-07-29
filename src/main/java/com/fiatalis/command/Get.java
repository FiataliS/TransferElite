package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.modelMessage.FileRequest;
import com.fiatalis.utils.Utils;

import java.io.IOException;

public class Get extends CommandRun {

    public Get(Attribute attribute) {
        super(attribute);
    }

    boolean isDelete = false;

    @Override
    public void help()  {
        Utils.printConsole("Загружает файл с сервера");
    }

    @Override
    void run() {
        try {
            if (attribute.getOptions().equals("-d")) {isDelete = true;}
        }catch (NullPointerException e){}


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
}
