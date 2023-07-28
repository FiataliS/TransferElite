package com.fiatalis.command;


import com.fiatalis.EchoServer;
import com.fiatalis.utils.Utils;


public class StopCommand extends CommandsRun {

    public StopCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Команда Stop выкдючает сервер");
    }

    @Override
    public void run() {
        EchoServer.getInstance().stopServer();
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
