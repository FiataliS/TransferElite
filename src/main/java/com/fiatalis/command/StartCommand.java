package com.fiatalis.command;

import com.fiatalis.EchoServer;
import com.fiatalis.utils.ThreadServerUtils;
import com.fiatalis.utils.Utils;


public class StartCommand extends CommandsRun {

    public StartCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Команда Start запускает сервер");
    }

    @Override
    public void run() {
        ThreadServerUtils.getInstance();
        if (ThreadServerUtils.getInstance().getThread() == null) {
            ThreadServerUtils.getInstance().setThread(new Thread(new EchoServer(), "server"));
        }
        if (ThreadServerUtils.getInstance().getThread().isAlive()) {
            System.out.println("Already working");
            return;
        }
        ThreadServerUtils.getInstance().getThread().start();
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
