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
    public void run() {
        EchoServer.getInstance().startServer();
    }

    @Override
    public void handler() {
        if (super.attribute.getAttribute() == null) {
            run();
        } else {
            attributeHandler();
        }
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
