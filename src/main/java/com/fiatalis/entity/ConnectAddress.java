package com.fiatalis.entity;

import lombok.Getter;

@Getter
public class ConnectAddress extends MyAddress {

    public ConnectAddress(String name, int port) {
        super(name, port);
    }

    @Override
    public EntityEnum getKey() {
        return EntityEnum.CONNECT_ADDRESS;
    }
}
