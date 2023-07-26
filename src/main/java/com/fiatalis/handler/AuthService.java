package com.fiatalis.handler;

import com.fiatalis.entity.User;

public class AuthService {
    public static Boolean checkUser(String login, String pass) {
        User user = new User();
        int myHash = pass.hashCode();
        int dbHash = user.getPassword();
        if (myHash == dbHash) {
            return true;
        }
        return false;
    }
}
