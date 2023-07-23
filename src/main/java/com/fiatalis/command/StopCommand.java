package com.fiatalis.command;

import lombok.Data;

@Data
public class StopCommand  extends CommandsRun {
    Thread thread;

    public StopCommand(Thread thread, Attribute attribute) {
        this.thread = thread;
        super.attribute = attribute;
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

    @Override
    public void handler() {
        if (super.attribute.getAttribute() == null) {
            run();
        } else {

        }
    }
}
