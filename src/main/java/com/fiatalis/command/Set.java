package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.Directory;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Set extends CommandRun {
    public Set(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда создаст/изменит настройки введите опцию: \n " +
                "set connect для изменения соединения  \n " +
                "set dir для изменения директории  \n " +
                "set user для изменения пользователя  \n " +
                "set server для изменения настроек сервера");
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == null) {
            System.out.println("Не введена опция обратитесь к помощи с помощью опции [-h]");
            return;
        }
        switch (attribute.getOptions()) {
            case CONNECT:
                connect();
                break;
            case DIR:
                dir();
                break;
            case USER:
                user();
                break;
            case SERVER:
                server();
                break;
        }
    }

    private void user() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите имя пользователя: ");
            User.getInstance().setName(reader.readLine().trim());
            System.out.print("Введите пороль: ");
            User.getInstance().setPassword(reader.readLine().trim());
        } catch (IOException e) {
        }
    }

    private void server() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите адресс сервера, по умолчанию будет localhost: ");
            ServerAddress.getInstance().setName(reader.readLine());
            System.out.print("Введите порт, по умолчанию будет 8797: ");
            ServerAddress.getInstance().setPort(reader.readLine());
        } catch (IOException e) {
        }
    }

    private void dir() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        try {
            System.out.print("Введите директорию, для сохранения: ");
            name = reader.readLine();
            if (name.length() < 1) {
                name = null;
            }
            Directory.getInstance().setName(name);
        } catch (IOException e) {
        }
    }


    private void connect() {
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
        ConnectAddress.getInstance().setName(name);
        ConnectAddress.getInstance().setPort(port);
    }
}
