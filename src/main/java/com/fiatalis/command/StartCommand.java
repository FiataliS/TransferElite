package com.fiatalis.command;

import com.fiatalis.EchoServer;
import com.fiatalis.handler.CommandHandler;
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
        if (CommandHandler.thread == null) {
            CommandHandler.thread = new Thread(new EchoServer(), "server");
        } else {

        }
        if (CommandHandler.thread.isAlive()) {
            System.out.println("Already working");
            return;
        }
        CommandHandler.thread.start();
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
