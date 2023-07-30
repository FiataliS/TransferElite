package com.fiatalis.client.command;

public class Help extends  CommandRun {
    public Help(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        System.out.println("Помощь");
    }

    private void helpRun() {
        System.out.println("команда не доработана");
    }

    @Override
    void optionsHandler() {
        help();
    }
}
