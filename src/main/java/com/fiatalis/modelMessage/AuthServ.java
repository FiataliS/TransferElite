package com.fiatalis.modelMessage;

import java.io.IOException;

public class AuthServ implements CloudMessage {

    String Nick;
    String pass;
    boolean auth;

    public AuthServ(String nick, String pass, boolean auth) throws IOException {
        this.Nick = nick;
        this.pass = pass;
        this.auth = auth;
    }

    public boolean getAuth() {
        return auth;
    }

    public String getNick() {
        return Nick;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.AUTH_SERV;
    }
}
