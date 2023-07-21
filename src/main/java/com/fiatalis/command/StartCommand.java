package com.fiatalis.command;

import com.fiatalis.EchoServer;
import lombok.Data;

@Data
public class StartCommand extends CommandWorker implements CommandLocalExecutor {
    Thread thread;

    public StartCommand() {
        commandsEnum = CommandsEnum.START;
    }


    @Override
    public void run() {
        if (thread == null) {
            thread = new Thread(new EchoServer(), "server");
        }
        if (thread.isAlive()) {
            System.out.println("Already working");
            return;
        }
        thread.start();
    }

}
