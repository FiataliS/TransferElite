package com.fiatalis.client.command;


import com.fiatalis.server.EchoServer;
import com.fiatalis.utils.Utils;


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
