package com.fiatalis.command;

public interface CommandLocalExecutor {
    void run();
    Thread getThread();
    void setThread(Thread thread);
}
