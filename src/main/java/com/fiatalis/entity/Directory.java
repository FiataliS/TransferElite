package com.fiatalis.entity;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class Directory {
    String name;

    {
        name = "TransferDirectory";
        setDir(Path.of(name));
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
            setDir(Path.of(name));
        }
    }

    private static volatile Directory instance;

    public static Directory getInstance() {
        Directory localInstance = instance;
        if (localInstance == null) {
            synchronized (Directory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Directory();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String toString() {
        return "Directory" + "\n" +
                "name: " + name;
    }

    private void setDir(Path path) {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
        }
    }
}
