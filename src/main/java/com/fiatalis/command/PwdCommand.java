package com.fiatalis.command;

public class PwdCommand extends CommandWorker implements CommandExternalPerformer {

    public PwdCommand() {
        commandsEnum = CommandsEnum.PWD;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }
}
