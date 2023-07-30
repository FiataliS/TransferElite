package com.fiatalis.entity;

import lombok.Getter;

@Getter
public class Server {
    String name;
    String port;

    private static volatile Server instance;

    {
        name = "localhost";
        port = "8189";
    }

    public void setName(String name) {
        if (name.length() < 1) {
            return;
        }
        this.name = name;
    }

    public void setPort(String port) {
        if (port.length() < 1) {
            return;
        }
        this.port = port;
    }

    public static Server getInstance() {
        Server localInstance = instance;
        if (localInstance == null) {
            synchronized (Server.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Server();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String toString() {
        return "MyAddress" + "\n" +
                "name: " + name + "\n" +
                "port: " + port;
    }

}
