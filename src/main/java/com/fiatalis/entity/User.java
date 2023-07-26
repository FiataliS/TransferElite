package com.fiatalis.entity;

import com.fiatalis.utils.ConfigUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Entity {
    String name;
    int password;

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password.hashCode();
    }

    @Override
    public void saveEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        configUtils.addNewOrUpdate(this);
    }

    @Override
    public Entity getEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        return configUtils.getEntity(EntityEnum.USER);
    }

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
        return new String[]{name, String.valueOf(password)};
    }

    @Override
    public String toString() {
        return "User" + "\n" +
                "name: " + name + "\n" +
                "password: " + password;
    }
}
