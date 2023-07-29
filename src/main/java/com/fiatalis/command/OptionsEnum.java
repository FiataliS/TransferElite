package com.fiatalis.command;

public enum OptionsEnum {
    DELETE("-D"),
    HELP("-H");

    private final String options;

    OptionsEnum(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

}
