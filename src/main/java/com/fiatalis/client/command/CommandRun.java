package com.fiatalis.client.command;

public abstract class CommandRun implements Command {
    Attribute attribute;

    public CommandRun(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public void handler() {
        if (attribute.getOptions() == OptionsEnum.HELP) {
            help();
        } else {
            optionsHandler();
        }
    }

    abstract void optionsHandler();
}
