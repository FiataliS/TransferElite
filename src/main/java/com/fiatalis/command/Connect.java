package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Connect extends CommandRun {

    public Connect(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это соединяется с сервером");
    }

    @Override
    public void run() {
        Client client = Client.getInstance();
        ConnectAddress connectAddress = ConnectAddress.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, password = null;
        try {
            System.out.print("Введите имя пользователя: ");
            name = reader.readLine();
            System.out.print("Введите пороль: ");
            password = reader.readLine();
        } catch (IOException e) {
        }
        if (connectAddress.getName().equals(null) || connectAddress.getPort().equals(null)) {
            System.out.println("Нет адреса и порта для подключения!");
            return;
        }
        if (!client.isAuthorized()) {
            client.connect(connectAddress);
            client.authentication(name, password);
            return;
        }
        System.out.println("Подключение прошло успешно: " + connectAddress.getName() + ":" + connectAddress.getPort());
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
