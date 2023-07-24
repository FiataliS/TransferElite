package com.fiatalis.command;

import com.fiatalis.entity.User;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetUserCommand extends CommandsRun {
    ConfigUtils configUtils = new ConfigUtils();

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
        User user = new User();
        System.out.print("Введите имя пользователя: ");
        try {
            user.setName(reader.readLine());
        } catch (IOException e) {
        }
        System.out.print("Введите пороль: ");
        try {
            user.setPassword(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configUtils.addNewOrUpdate(user);
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
