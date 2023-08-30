package com.fiatalis.client.command;

import com.fiatalis.client.Client;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Connect extends CommandRun {

    public Connect(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        connect();
    }

    private void connect() {
        Client client = Client.getInstance();
        com.fiatalis.entity.Connect connect = com.fiatalis.entity.Connect.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, password = null;
        try {
            System.out.print("Введите имя пользователя: ");
            name = reader.readLine();
            System.out.print("Введите пороль: ");
            password = reader.readLine();
        } catch (IOException e) {
        }
        if (connect.getName().equals(null) || connect.getPort().equals(null)) {
            System.out.println("Нет адреса и порта для подключения!");
            return;
        }
        if (!client.isAuthorized()) {
            client.connect(connect);
            client.authentication(name, password);
            return;
        }
        System.out.println("Подключение прошло успешно: " + connect.getName() + ":" + connect.getPort());
    }
}
