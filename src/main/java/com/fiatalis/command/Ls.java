package com.fiatalis.command;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Language;
import com.fiatalis.utils.Utils;

import java.util.ResourceBundle;

public class Ls extends CommandRun {

    public Ls(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        ls();
    }

    private void ls() {
        ResourceBundle rb = ResourceBundle.getBundle("consoleMsg", Language.getInstance().getLocate());
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().getServerView().size() == 0) ;
            Client.getInstance().getServerView().forEach(System.out::println);
        } catch (NullPointerException e) {
            Utils.printConsole(rb.getString("failedConnect"), true);
        }
    }
}
