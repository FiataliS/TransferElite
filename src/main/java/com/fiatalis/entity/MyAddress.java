package com.fiatalis.entity;

import lombok.Getter;

@Getter
public class MyAddress implements Entity {
    String name;
    int port;

    public MyAddress(String name, String port) {
        this.name = name;
        if (port == null) {
            this.port = 0;
            return;
        }
        this.port = Integer.valueOf(port);
    }

    @Override
    public EntityEnum getKey() {
        return EntityEnum.MY_ADDRESS;
    }

    @Override
    public String[] getOptionName() {
        return new String[]{"name", "port"};
    }

    @Override
    public String[] getObjectValue() {
        return new String[]{name, String.valueOf(port)};
    }

    @Override
    public String toString() {
        return "MyAddress" + "\n" +
                "name: " + name + "\n" +
                "port: " + port;
    }

}
