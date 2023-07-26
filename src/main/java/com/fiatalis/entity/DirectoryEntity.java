package com.fiatalis.entity;

import com.fiatalis.utils.ConfigUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DirectoryEntity implements Entity {
    String name;

    @Override
    public EntityEnum getKey() {
        return EntityEnum.DIR;
    }

    @Override
    public String[] getOptionName() {
        return new String[]{"Имя"};
    }

    @Override
    public String[] getObjectValue() {
        return new String[]{name};
    }

    @Override
    public void saveEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        configUtils.addNewOrUpdate(this);
    }

    @Override
    public Entity getEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        return configUtils.getEntity(EntityEnum.DIR);
    }

    @Override
    public String toString() {
        return "Directory" + "\n" +
                "name: " + name;
    }

}
