package com.fiatalis.entity;

import lombok.Data;

@Data
public class ConnectAddress extends ServerAddress {

    private static volatile ConnectAddress instance;

    {
        super.name = null;
        super.port = null;
    }

    public static ConnectAddress getInstance() {
        ConnectAddress localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectAddress.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectAddress();
                }
            }
        }
        return localInstance;
    }
    @Override
    public String toString() {
        return "ConnectAddress" + "\n" +
                "name: " + name + "\n" +
                "port: " + port;
    }
}
