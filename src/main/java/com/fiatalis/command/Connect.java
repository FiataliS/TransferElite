package com.fiatalis.command;

import com.fiatalis.client.Client;

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
        String name, password;
        try {
            System.out.print("Введите имя пользователя: ");
            name = reader.readLine();
            System.out.print("Введите пороль: ");
            password = reader.readLine();
            if (!client.isAuthorized()) {
                client.connect(connect);
                client.authentication(name, password);
                return;
            }
        } catch (NullPointerException e) {
            System.out.println("Нет адреса или порта для подключения!");
        } catch (IOException e) {
        }
        System.out.println("Подключение прошло успешно: " + connect.getName() + ":" + connect.getPort());
    }
}
