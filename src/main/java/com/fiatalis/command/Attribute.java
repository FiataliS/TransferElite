package com.fiatalis.command;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Attribute {
    private CommandsEnum commandsEnum = CommandsEnum.NOT_FOUND;
    private String attribute;
    private String options;
    private List<String> list;

    public Attribute(String string) {
        list = parsing(string);
        searchCommand();
        attribute = checkAttribute(1);
        options = checkAttribute(2);
    }

    private String checkAttribute(int check) {
        try {
            list.get(check);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return list.get(check);
    }


    private void searchCommand() {
        if (checkAttribute(0).toUpperCase().equals("SET") && list.size() >= 2) {
            switch (checkAttribute(1).toUpperCase()) {
                case "USER":
                    commandsEnum = CommandsEnum.SET_USER;
                    break;
                case "SERVER":
                    commandsEnum = CommandsEnum.SET_SERVER;
                    break;
                case "CONNECT":
                    commandsEnum = CommandsEnum.SET_CONNECT;
                    break;
            }
            list.remove(0);
            list.set(0, commandsEnum.toString());
        }

        if (checkAttribute(0).toUpperCase().equals("SAVE") && list.size() >= 2) {
            if (checkAttribute(1).toUpperCase().equals("OPT")) {
                    commandsEnum = CommandsEnum.SAVE_OPT;
            }
            list.remove(0);
            list.set(0, commandsEnum.toString());
        }

        try {
            commandsEnum = CommandsEnum.valueOf(checkAttribute(0).toUpperCase());
        } catch (IllegalArgumentException e) {
        }
    }

    private List<String> parsing(String string) {
        return Stream.of(string.split(" ")).collect(Collectors.toList());
    }
}
