package com.fiatalis.command;


import com.fiatalis.utils.ThreadServerUtils;
import com.fiatalis.utils.Utils;


public class StopCommand extends CommandsRun {

    public StopCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Команда Stop выкдючает сервер");
    }

    @Override
    public void run() {
        try {
            ThreadServerUtils.getInstance().getThread().interrupt();
            ThreadServerUtils.getInstance().setThread(null);
        } catch (NullPointerException e) {
            System.out.println("Please enter start");
        }
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
