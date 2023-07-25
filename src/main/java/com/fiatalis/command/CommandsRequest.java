package com.fiatalis.command;

public interface CommandsRequest extends Commands {
    void receiveCommand();
    void transferCommand();
}
