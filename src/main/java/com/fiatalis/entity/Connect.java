package com.fiatalis.entity;

import lombok.Data;

@Data
public class Connect extends Server {

    private static volatile Connect instance;

    {
        super.name = null;
        super.port = null;
    }

    public static Connect getInstance() {
        Connect localInstance = instance;
        if (localInstance == null) {
            synchronized (Connect.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Connect();
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
