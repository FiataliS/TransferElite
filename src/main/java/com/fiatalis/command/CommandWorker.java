package com.fiatalis.command;


public abstract class CommandWorker implements Command {
    CommandsEnum commandsEnum;
    public String getHelp() {
        return "Это команда: " + commandsEnum.name();
    }
}
