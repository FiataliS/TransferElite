package com.fiatalis.command;

public class LsCommand extends CommandWorker implements CommandExternalPerformer{

    public LsCommand() {
      commandsEnum = CommandsEnum.LS;
    }

    @Override
    public void receiveCommand() {

    }

    @Override
    public void transferCommand() {

    }


}
