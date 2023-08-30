package com.fiatalis.client.command;

import com.fiatalis.server.EchoServer;
import com.fiatalis.utils.Utils;


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
