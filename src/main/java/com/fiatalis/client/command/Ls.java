package com.fiatalis.client.command;

import com.fiatalis.client.Client;
import com.fiatalis.utils.Utils;

public class Ls extends CommandRun {

    public Ls(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Показывает список файлов на сервере");
    }

    @Override
    public void optionsHandler() {
        ls();
    }

    private void ls() {
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().getServerView().size() == 0) ;
            Client.getInstance().getServerView().forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("Требуется соедениться с сервером");
        }
    }
}
