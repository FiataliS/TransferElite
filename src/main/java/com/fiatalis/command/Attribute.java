package com.fiatalis.command;

import lombok.Data;

import java.util.List;

@Data
public class Attribute {
    CommandsEnum command;
    String attribute;
    String property;

    public Attribute(List<String> list) {
        command = CommandsEnum.valueOf(checkAttribute(list ,0).toUpperCase());
        attribute = checkAttribute(list,1);
        property = checkAttribute(list,2);
    }

    private String checkAttribute(List<String> list, int check) {
        try {
            list.get(check);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return list.get(check);
    }
}
