package com.fiatalis.command;

public class MkdirCommand extends CommandWorker implements CommandExternalPerformer {

    public MkdirCommand() {
        commandsEnum = CommandsEnum.MKDIR;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }
}
