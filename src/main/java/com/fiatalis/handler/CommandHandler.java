package com.fiatalis.handler;

import com.fiatalis.command.*;

public class CommandHandler {
    private Command command = null;
    Attribute attribute;

    public CommandHandler(String string) {
        attribute = new Attribute(string);
    }

    public void commandDefinition() {
        switch (attribute.getCommand()) {
            case LS:
                command = new Ls(attribute);
                break;
            case START:
                command = new Start(attribute);
                break;
            case STOP:
                command = new Stop(attribute);
                break;
            case OPT:
                command = new Opt(attribute);
                break;
            case SET:
                command = new Set(attribute);
                break;
            case EXIT:
                command = new Exit(attribute);
                break;
            case SPACE:
                break;
            case SAVE:
                command = new Save(attribute);
                break;
            case CONNECT:
                command = new Connect(attribute);
                break;
            case GET:
                command = new Get(attribute);
                break;
            case PUT:
                command = new Put(attribute);
            default:
                System.out.println("Команда не наедена");
        }
        if (command != null) {
            command.handler();
        }
    }
}
