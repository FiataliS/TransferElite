package com.fiatalis.command;

import com.fiatalis.utils.Utils;

public class Exit extends CommandRun {
    public Exit(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда выходит из приложения");
    }

    @Override
    void run() {
        System.exit(0);
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
