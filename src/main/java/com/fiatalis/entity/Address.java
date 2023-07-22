package com.fiatalis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address implements Entity{
    String name;
    int port;

    @Override
    public EntityEnum getKey() {
        return EntityEnum.ADDRESS;
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
