package com.fiatalis.command;

import com.fiatalis.EchoServer;
import com.fiatalis.utils.Utils;


public class Start extends CommandRun {

    public Start(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Команда Start запускает сервер");
    }

    @Override
    public void optionsHandler() {
        start();
    }

    public void start() {
        EchoServer.getInstance().startServer();
    }
}
