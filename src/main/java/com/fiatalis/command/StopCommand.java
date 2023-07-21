package com.fiatalis.command;

import lombok.Data;

@Data
public class StopCommand extends CommandWorker implements CommandLocalExecutor {
    Thread thread;

    public StopCommand() {
        commandsEnum = CommandsEnum.STOP;
    }

    @Override
    public void run() {
        try {
            thread.interrupt();
            thread = null;
        } catch (NullPointerException e) {
            System.out.println("Please enter start");
        }
    }
}
