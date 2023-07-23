package com.fiatalis.handler;

import com.fiatalis.command.*;

import java.util.List;

public class CommandHandler {
    public static Thread thread = null;
    private Commands command = null;
    private List<String> query;

    public CommandHandler(List<String> query) {
        this.query = query;
    }

    public void commandDefinition() {
        Attribute attribute = new Attribute(query);
        switch (attribute.getCommand()) {
            case LS:
                command = new LsCommands(attribute);
                break;
            case START:
                command = new StartCommand(attribute);
                break;
            case STOP:
                command = new StopCommand(attribute);
                break;
            case OPTIONS:
                command = new OptionsCommand(attribute);
        }

        if (command != null) {
            command.handler();
        }
    }
}
