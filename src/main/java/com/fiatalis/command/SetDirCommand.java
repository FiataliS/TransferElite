package com.fiatalis.command;

import com.fiatalis.entity.Directory;
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
        String name;
        try {
            System.out.print("Введите директорию, для сохранения: ");
            name = reader.readLine();
            if (name.length() < 1){
                name = null;
            }
            Directory.getInstance().setName(name);
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
