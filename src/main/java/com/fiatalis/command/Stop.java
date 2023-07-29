package com.fiatalis.command;


import com.fiatalis.EchoServer;
import com.fiatalis.utils.Utils;


public class Stop extends CommandRun {

    public Stop(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Команда Stop выкдючает сервер");
    }

    @Override
    public void optionsHandler() {
        stop();
    }

    public void stop() {
        EchoServer.getInstance().stopServer();
    }
}
