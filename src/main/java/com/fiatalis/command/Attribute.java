package com.fiatalis.command;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Attribute {
    private CommandsEnum commandsEnum = CommandsEnum.NOT_FOUND;
    private String attribute;
    private String property;
    private List<String> list;

    public Attribute(String string) {
        list = parsing(string);
        searchCommand();
        attribute = checkAttribute(1);
        property = checkAttribute(2);
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
        if (checkAttribute(0).toUpperCase().equals("SET")) {
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
        try {
            commandsEnum = CommandsEnum.valueOf(checkAttribute(0).toUpperCase());
        } catch (IllegalArgumentException e) {

        }
    }

    private List<String> parsing(String string) {
        return Stream.of(string.split(" ")).collect(Collectors.toList());
    }

}
