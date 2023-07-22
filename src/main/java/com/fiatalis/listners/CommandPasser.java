package com.fiatalis.listners;

import com.fiatalis.command.*;
import com.fiatalis.utils.Utils;

public class CommandPasser {
    Thread thread = null;

    public void readerCommand(String s) throws IllegalArgumentException {
        if (s.equals("")) {
            return;
        }
        Command command = null;
        switch (CommandsEnum.valueOf(s.toUpperCase())) {
            case LS:
                command = new LsCommand();
                var ls = (CommandExternalPerformer) command;

                break;
            case CD:
                command = new CdCommand();
                break;
            case RM:
                System.out.println("Удалить файл, папку");
                break;
            case MKDIR:
                System.out.println("Создать папку");
                break;
            case CAT:
                command = new CatCommand();
                break;
            case START:
                command = new StartCommand();
                var startCommand = (CommandLocalExecutor) command;
                startCommand.setThread(thread);
                startCommand.run();
                thread = startCommand.getThread();
                break;
            case STOP:
                command = new StopCommand();
                var stopCommand = (CommandLocalExecutor) command;
                stopCommand.setThread(thread);
                stopCommand.run();
                thread = stopCommand.getThread();
                break;
        }
        Utils.printConsole(command.getHelp());
    }

}