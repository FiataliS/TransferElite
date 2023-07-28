package com.fiatalis.utils;

import com.fiatalis.entity.User;

public class AuthServiceUtils {
    public static Boolean checkUser(String name, String pass) {
        User user = User.getInstance();
        int myHash = pass.hashCode();
        int dbHash = user.getPassHash();
        if (user.getName().equals(name)) {
            if (myHash == dbHash) {
                System.out.println("Подключился пользователь: " + name);
                return true;
            }
            System.out.println("Пользователь: " + name + " ввел не правильный пороль");
            return false;
        }
        System.out.println("Пользователь: " + name + " отсутствет");
        return false;
    }
}
