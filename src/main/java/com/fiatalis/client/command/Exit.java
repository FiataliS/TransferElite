package com.fiatalis.client.command;

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
    public void optionsHandler() {
        exit();
    }

    private void exit() {
        System.exit(0);
    }
}
