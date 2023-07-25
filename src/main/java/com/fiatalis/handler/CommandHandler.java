package com.fiatalis.handler;

import com.fiatalis.command.*;

public class CommandHandler {
    private Commands command = null;
    Attribute attribute;

    public CommandHandler(String string) {
        attribute = new Attribute(string);
    }

    public void commandDefinition() {
        switch (attribute.getCommandsEnum()) {
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
                break;
            case SET_USER:
                command = new SetUserCommand(attribute);
                break;
            case SET_SERVER:
                command = new SetServerCommand(attribute);
                break;
            case SET_CONNECT:
                command = new SetConnect(attribute);
                break;
            default:
                System.out.println("Команда не наедена");
        }
        if (command != null) {
            command.handler();
        }
    }
}
