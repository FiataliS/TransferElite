package com.fiatalis.entity;

import com.fiatalis.utils.ConfigUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConnectAddress extends ServerAddress {

    public ConnectAddress(String name, String port) {
        this.name = name;
        if (port == null) {
            this.port = 0;
            return;
        }
        this.port = Integer.valueOf(port);
    }



    @Override
    public void saveEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        configUtils.addNewOrUpdate(this);
    }

    @Override
    public Entity getEntity() {
        ConfigUtils configUtils = new ConfigUtils();
        return configUtils.getEntity(EntityEnum.CONNECT_ADDRESS);
    }

    @Override
    public EntityEnum getKey() {
        return EntityEnum.CONNECT_ADDRESS;
    }

    @Override
    public String toString() {
        return "ConnectAddress" + "\n" +
                "name: " + name + "\n" +
                "port: " + port;
    }
}
