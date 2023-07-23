package com.fiatalis.command;

import com.fiatalis.EchoServer;
import lombok.Data;

@Data
public class StartCommand extends CommandsRun {
    Thread thread;

    public StartCommand(Thread thread, Attribute attribute) {
        this.thread = thread;
        super.attribute = attribute;
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

    @Override
    public void handler() {
        if (super.attribute.getAttribute() == null) {
            run();
        } else {

        }
    }
}
