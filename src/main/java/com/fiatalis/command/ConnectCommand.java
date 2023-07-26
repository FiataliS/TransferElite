package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.utils.Utils;

public class ConnectCommand extends CommandsRun {
    public ConnectCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это соединяется с сервером");
    }

    @Override
    public void run() {
        Client client = Client.getInstance();
        client.connect("localhost", 8189);
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
