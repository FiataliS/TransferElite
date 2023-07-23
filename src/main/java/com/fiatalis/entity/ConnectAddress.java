package com.fiatalis.entity;

import lombok.Getter;

@Getter
public class ConnectAddress extends MyAddress {

    public ConnectAddress(String name, String port) {
        super(name, port);
    }

    @Override
    public EntityEnum getKey() {
        return EntityEnum.CONNECT_ADDRESS;
    }

    @Override
    public String toString() {
        return "ConnectAddress{" +
                "name='" + name + '\'' +
                ", port=" + port +
                '}';
    }
}
