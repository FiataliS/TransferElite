package com.fiatalis.command;

import com.fiatalis.entity.DirectoryEntity;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetDirCommand extends CommandsRun{
    public SetDirCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда создаст/изменит директорию по умолчанию.");
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        try {
            System.out.print("Введите директорию, для сохранения по умолчанию: ");
            name = reader.readLine();
            if (name.length() < 1) name = null;
        } catch (IOException e) {
        }
        new DirectoryEntity(name).saveEntity();
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
