package com.fiatalis.client.command;

import lombok.Data;

import java.util.*;

@Data
public class Attribute {
    private CommandsEnum command = CommandsEnum.NOT_FOUND;
    private OptionsEnum options;
    private String attribute;

    private List<String> list = new ArrayList<>();
    private String receivedString;

    public Attribute(String string) {
        if (string.length() < 1) command = CommandsEnum.SPACE;
        receivedString = string;
        parsing(string);
        searchCommand();
        if (command.equals(CommandsEnum.NOT_FOUND) || command.equals(CommandsEnum.SPACE)) return;
        searchOptions();
        searchAttribute();
    }

    private void searchAttribute() {
        StringBuilder sb = new StringBuilder(receivedString.trim());
        if (options != null) sb.delete(sb.length() - options.getOptions().length(), sb.length());
        if (list.size() > 1) sb.delete(0, command.name().length());
        attribute = sb.toString().trim();
    }

    private void searchOptions() {
        for (int i = 0; i < OptionsEnum.values().length; i++) {
            if (OptionsEnum.values()[i].getOptions().equals(list.get(list.size() - 1).toUpperCase())) {
                options = OptionsEnum.values()[i];
            }
        }
    }

    private void searchCommand() {
        try {
            command = CommandsEnum.valueOf(list.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
        }
    }

    private void parsing(String string) throws NullPointerException {
        for (String s : string.split(" ")) {
            list.add(s);
        }
    }
}
