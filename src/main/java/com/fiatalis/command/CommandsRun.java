package com.fiatalis.command;

public abstract class CommandsRun implements Commands {
    Attribute attribute;

    public CommandsRun(Attribute attribute) {
        this.attribute = attribute;
    }

    abstract void help();

    abstract void run();

    public void handler() {
        if (attribute.getAttribute() == null) {
            run();
        } else {
            attributeHandler();
        }
    }

    abstract void attributeHandler();
}
