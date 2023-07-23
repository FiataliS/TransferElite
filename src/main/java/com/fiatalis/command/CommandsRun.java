package com.fiatalis.command;

public abstract class CommandsRun implements Commands {
    Attribute attribute;
    abstract void run();
}
