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
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().getServerView().size() == 0);
            Client.getInstance().getServerView().forEach(System.out::println);
        } catch (NullPointerException e){
            System.out.println("Требуется соедениться с сервером");
        }
        
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
