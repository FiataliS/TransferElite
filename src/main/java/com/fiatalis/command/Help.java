package com.fiatalis.command;

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
        ResourceBundle rb = ResourceBundle.getBundle("consoleHelp", new Locale(Language.getInstance().getLanguage()));
        for (String s : rb.keySet()) {
            Utils.printConsole(s + ": " + rb.getString(s), true);
        }
    }

    @Override
    void optionsHandler() {
        help();
    }
}
