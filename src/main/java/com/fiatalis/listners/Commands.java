package com.fiatalis.listners;

public enum Commands {
    LS("ls"),
    CD("cd"),
    MKDIR("mkdir"),
    RM("rm"),
    CAT("cat"),
    PWD("pwd"),
    START("start"),
    STOP("stop");

    private final String commands;

    Commands(String commands) {
        this.commands = commands;
    }

}
