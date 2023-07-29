package com.fiatalis.command;

public abstract class CommandRun implements Command {
    Attribute attribute;

    public CommandRun(Attribute attribute) {
        this.attribute = attribute;
    }

    abstract void run();

    @Override
    public void handler() {
        if (attribute.getAttribute() == null) {
            run();
        } else {
            attributeHandler();
        }
    }

    abstract void attributeHandler();
}
