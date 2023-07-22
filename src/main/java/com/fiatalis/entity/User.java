package com.fiatalis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class User implements Entity {
    String name;
    String password;

    @Override
    public EntityEnum getKey() {
        return EntityEnum.USER;
    }

    @Override
    public String[] getOptionName() {
        return new String[]{"name","password"};
    }

    @Override
    public String[] getObjectValue() {
        return new String[]{name,password};
    }


}
