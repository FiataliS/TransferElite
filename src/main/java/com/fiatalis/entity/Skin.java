package com.fiatalis.entity;

public class Skin {
    Boolean skin;

    public Skin() {
        skin = false;
    }

    public void setSkin(Boolean skin) {
        this.skin = skin;
    }

    public Boolean getSkin() {
        return skin;
    }

    private static volatile Skin instance;

    public static Skin getInstance() {
        Skin localInstance = instance;
        if (localInstance == null) {
            synchronized (Skin.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Skin();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String toString() {
        return "Interface" + "\n" +
                "shell: " + skin;
    }
}
