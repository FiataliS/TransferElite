package com.fiatalis.command;

import com.fiatalis.entity.ServerAddress;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetServer extends CommandRun {
    public SetServer(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда создаст/изменит порт и адрес для соединения с вами.");
    }

    @Override
    void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите адресс сервера, по умолчанию будет localhost: ");
            ServerAddress.getInstance().setName(reader.readLine());
            System.out.print("Введите порт, по умолчанию будет 8797: ");
            ServerAddress.getInstance().setPort(reader.readLine());
        } catch (IOException e) {
        }
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
