package com.fiatalis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
    private String name;
    private String password;
    private int passHash;
    private static volatile User instance;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.passHash = password.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
        this.passHash = password.hashCode();
    }

    public static User getInstance() {
        User localInstance = instance;
        if (localInstance == null) {
            synchronized (User.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new User();
                }
            }
        }
        return localInstance;
    }


    @Override
    public String toString() {
        return "User" + "\n" +
                "name: " + name + "\n" +
                "password: " + password;
    }
}
