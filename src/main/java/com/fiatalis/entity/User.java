package com.fiatalis.entity;

public class User {
    private String name;
    private String password;
    private int passHash;
    private static volatile User instance;

    public String getName() {
        return name;
    }

    public int getPassHash() {
        return passHash;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setPassword(String password) {
        this.password = password;
        this.passHash = password.hashCode();
    }

    public void setPassHash(String passHash){
        this.passHash = Integer.parseInt(passHash);
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
                "password: " + passHash;
    }
}
