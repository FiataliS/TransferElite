package com.fiatalis.command;


import com.fiatalis.server.EchoServer;


public class Stop extends CommandRun {

    public Stop(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        stop();
    }

    public void stop() {
        EchoServer.getInstance().stopServer();
    }
}
