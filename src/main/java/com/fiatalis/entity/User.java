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
    String password;

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
        return new String[]{name,password};
    }

    @Override
    public String toString() {
        return "User" + "\n" +
                "name: " + name + "\n" +
                "password: " + password;
    }
}
