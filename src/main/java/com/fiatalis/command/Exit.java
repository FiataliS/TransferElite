package com.fiatalis.command;


public class Exit extends CommandRun {
    public Exit(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        exit();
    }

    private void exit() {
        System.exit(0);
    }
}
