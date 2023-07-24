package com.fiatalis.entity;

import com.fiatalis.utils.ConfigUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServerAddress implements Entity {
    String name;
    int port;

    @Override
    public void saveEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        configUtils.addNewOrUpdate(this);
    }

    @Override
    public Entity getEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        return configUtils.getEntity(EntityEnum.SERVER_ADDRESS);
    }

    public ServerAddress(String name, String port) {
        if (name == null) {
            this.name = "localhost";
        } else {
            this.name = name;
        }
        if (port == null) {
            this.port = 0;
            return;
        }
        this.port = Integer.valueOf(port);
    }

    @Override
    public EntityEnum getKey() {
        return EntityEnum.SERVER_ADDRESS;
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
