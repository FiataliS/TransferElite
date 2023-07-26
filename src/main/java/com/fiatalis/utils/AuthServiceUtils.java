package com.fiatalis.utils;

import com.fiatalis.entity.User;

public class AuthServiceUtils {
    public static Boolean checkUser(String login, String pass) {
        User user = (User) new User().getEntity();
        int myHash = pass.hashCode();
        int dbHash = Integer.parseInt(user.getEntity().getObjectValue()[1]);
        if (myHash == dbHash) {
            return true;
        }
        return false;
    }
}
