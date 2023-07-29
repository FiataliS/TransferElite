package com.fiatalis.command;

import com.fiatalis.entity.User;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetUser extends CommandRun {

    public SetUser(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда создаст нового пользователя");
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите имя пользователя: ");
            User.getInstance().setName(reader.readLine().trim());
            System.out.print("Введите пороль: ");
            User.getInstance().setPassword(reader.readLine().trim());
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
