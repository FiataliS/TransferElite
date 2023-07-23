package com.fiatalis.handler;

import com.fiatalis.command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    Thread thread = null;
    Commands command = null;
    List<String> query;

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
                command = new StartCommand(thread, attribute);
                //var startCommand = (StartCommand) command;
//                startCommand.setThread(thread);
//                startCommand.run();
//                thread = startCommand.getThread();
                break;
            case STOP:
                command = new StopCommand(thread, attribute);
//                var stopCommand = (StopCommand) command;
//                stopCommand.setThread(thread);
//                stopCommand.run();
//                thread = stopCommand.getThread();
                break;
        }
        if (command != null) {
            command.handler();
        }
    }
}
