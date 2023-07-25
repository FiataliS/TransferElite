package com.fiatalis.command;

import com.fiatalis.entity.User;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetUserCommand extends CommandsRun {

    public SetUserCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Это команда создаст нового пользователя");
    }

    @Override
    void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null, password = null;
        try {
            System.out.print("Введите имя пользователя: ");
            name = reader.readLine();
            System.out.print("Введите пороль: ");
            password = reader.readLine();
        } catch (IOException e) {
        }
        new User(name, password).saveEntity();
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
