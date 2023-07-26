package com.fiatalis.modelMessage;

import com.fiatalis.entity.Entity;
import com.fiatalis.entity.User;
import lombok.Data;
import lombok.Getter;

import java.io.IOException;

@Data
@Getter
public class AuthServ implements CloudMessage {
    private String name;
    private String pass;
    private boolean auth;

    public AuthServ(String name, String pass, boolean auth) throws IOException {
        this.name = name;
        this.pass = pass;
        this.auth = auth;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.AUTH_SERV;
    }
}
