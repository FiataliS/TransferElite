package com.fiatalis.command;

public class RmCommand extends CommandWorker implements CommandExternalPerformer {

    public RmCommand() {
        commandsEnum = CommandsEnum.RM;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }
}
