package com.fiatalis.command;

public enum OptionsEnum {
    DELETE("-D"),
    HELP("-H"),
    OPT("OPT"),
    CONNECT("CONNECT"),
    DIR("DIR"),
    SERVER("SERVER"),
    USER("USER");

    private final String options;

    OptionsEnum(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

}
