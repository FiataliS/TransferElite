package com.fiatalis.client.command;

import com.fiatalis.entity.Language;
import com.fiatalis.utils.Utils;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class CommandRun implements Command {
    Attribute attribute;

    public CommandRun(Attribute attribute) {
        this.attribute = attribute;
    }

    public void help() {
        ResourceBundle rb = ResourceBundle.getBundle("consoleHelp", new Locale(Language.getInstance().getLanguage()));
        Utils.printConsole(attribute.getCommand().toString() + ": " + rb.getString(attribute.getCommand().toString()));
    }

    @Override
    public void handler() {
        if (attribute.getOptions() == OptionsEnum.HELP) {
            help();
        } else {
            optionsHandler();
        }
    }

    abstract void optionsHandler();
}
