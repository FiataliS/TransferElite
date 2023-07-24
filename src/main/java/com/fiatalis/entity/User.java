package com.fiatalis.entity;

import lombok.*;

@Data
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "User" + "\n" +
                "name: " + name + "\n" +
                "password: " + password;
    }
}
