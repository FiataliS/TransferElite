package com.fiatalis.listners;

public enum Commands {
    LS("ls"),
    CD("cd"),
    MKDIR("mkdir"),
    RM("rm"),
    CAT("cat");

    private final String commands;

    Commands(String commands) {
        this.commands = commands;
    }

}
