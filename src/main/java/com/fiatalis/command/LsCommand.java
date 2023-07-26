package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.utils.Utils;


public class LsCommand extends CommandsRun {


    public LsCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Показывает список файлов на сервере");
    }

    @Override
    public void run() {
        Client.getInstance().updateServerViewPath();
        Client.getInstance().serverView.forEach(System.out::println);

    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
