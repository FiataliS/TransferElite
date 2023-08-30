package com.fiatalis.command;

import com.fiatalis.server.EchoServer;


public class Start extends CommandRun {

    public Start(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        start();
    }

    public void start() {
        EchoServer.getInstance().startServer();
    }
}
