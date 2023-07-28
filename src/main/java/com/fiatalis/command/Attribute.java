package com.fiatalis.command;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

        if (list.get(0).toUpperCase().equals("GET") && list.size() >= 2 || list.get(0).toUpperCase().equals("PUT") && list.size() >= 2) {
            if (checkAttribute(list.size() - 1).toUpperCase().equals("-D")) {
                options = "-d";
                list.remove(list.size() - 1);
            } else {
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < list.size(); i++) {
                sb.append(list.get(i) + " ");

            }
            list.set(1, sb.toString().trim());
            if (checkAttribute(2) == null) {
                list.add(options);
            }
            list.set(2, options);
        }
        try {
            commandsEnum = CommandsEnum.valueOf(checkAttribute(0).toUpperCase());
        } catch (IllegalArgumentException e) {
        }
    }

    private List<String> parsing(String string) {
        List<String> result = new ArrayList<>();
        for (String s : string.split(" ")) {
            result.add(s);
        }
        return result;
    }
}
