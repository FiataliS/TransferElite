package com.fiatalis.client.command;

import com.fiatalis.entity.Language;
import com.fiatalis.utils.Utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Help extends CommandRun {
    public Help(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        ResourceBundle rb = ResourceBundle.getBundle("messages", new Locale(Language.getInstance().getLanguage()));
        for (String s : rb.keySet()) {
            Utils.printConsole(s + ": " + rb.getString(s));
        }
    }

    private void helpRun() {
        System.out.println("команда не доработана");
    }

    @Override
    void optionsHandler() {
        help();
    }
}
