package com.fiatalis.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyAddress implements Entity{
    String name;
    int port;

    @Override
    public EntityEnum getKey() {
        return EntityEnum.MY_ADDRESS;
    }

    @Override
    public String[] getOptionName() {
        return new String[]{"name","port"};
    }

    @Override
    public String[] getObjectValue() {
        return new String[]{name, String.valueOf(port)};
    }
}
