package com.fiatalis.command;

import com.fiatalis.entity.ServerAddress;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetServerCommand extends CommandsRun {
    public SetServerCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Это команда создаст/изменит порт и адрес для соединения с вами.");
    }

    @Override
    void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, port = null;
        try {
            System.out.print("Введите адресс сервера, по умолчанию будет localhost: ");
            name = reader.readLine();
            if (name.length() < 1) name = null;
            System.out.print("Введите порт, по умолчанию будет 8797: ");
            port = reader.readLine().trim();
            if (port.length() < 1) port = null;
        } catch (IOException e) {
        }
        new ServerAddress(name, port).saveEntity();
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
