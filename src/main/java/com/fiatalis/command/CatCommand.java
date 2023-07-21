package com.fiatalis.command;

public class CatCommand extends CommandWorker implements CommandExternalPerformer {

    public CatCommand() {
        commandsEnum = CommandsEnum.CAT;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }
}
