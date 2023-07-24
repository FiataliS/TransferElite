package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetConnect extends CommandsRun {
    public SetConnect(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Это команда создаст/изменит порт и адрес сервера для соединения.");
    }

    @Override
    void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, port = null;
        try {
            System.out.print("Введите адресс сервера: ");
            name = reader.readLine();
            if (name.length() < 1) name = null;
            System.out.print("Введите порт: ");
            port = reader.readLine();
            if (port.length() < 1) port = null;
        } catch (IOException e) {
        }
        new ConnectAddress(name, port).saveEntity();
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
