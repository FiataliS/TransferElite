package com.fiatalis.command;

public class CdCommand extends CommandWorker implements CommandExternalPerformer {
    public CdCommand() {
        commandsEnum = CommandsEnum.CD;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }
}
