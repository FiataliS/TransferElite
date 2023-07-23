package com.fiatalis.utils;

import com.fiatalis.entity.*;
import org.ini4j.Profile.*;
import org.ini4j.Wini;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ConfigBuilding {
    private Wini ini;
    private final String INI_FILE = "saves/TransferEliteConfig.ini";

    public ConfigBuilding() throws IOException {
        ini = new Wini(getFile());

        Entity entity = getEntity(EntityEnum.CONNECT_ADDRESS);

        for (int i = 0; i < entity.getOptionName().length; i++) {
            System.out.println(entity.getObjectValue()[i]);
        }
    }

    public void addNewOrUpdate(Entity entity) {
        String sectionName = getSectionName(entity);
        for (int i = 0; i < entity.getOptionName().length; i++) {
            ini.put(sectionName, entity.getOptionName()[i], entity.getObjectValue()[i]);
        }
        updateIniFile();
    }

    public Entity getEntity(EntityEnum name) {
        String[] objectValue = new String[2];
        Set<Map.Entry<String, Section>> sections = ini.entrySet();
        for (Map.Entry<String, Section> s : sections) {
            if (s.getKey().equals(name.toString())) {
                objectValue = s.getValue().values().toArray(String[]::new);
            }
        }
        Entity entity = null;
        switch (name) {
            case USER:
                entity = new User(objectValue[0], objectValue[1]);
                break;
            case MY_ADDRESS:
                entity = new MyAddress(objectValue[0], Integer.valueOf(objectValue[1]));
                break;
            case CONNECT_ADDRESS:
                entity = new ConnectAddress(objectValue[0], Integer.valueOf(objectValue[1]));
                break;
        }
        return entity;
    }


    private void updateIniFile() {
        try {
            ini.store(new FileOutputStream(INI_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile() {
        File file = new File(INI_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private String getSectionName(Entity entity) {
        Set<Map.Entry<String, Section>> sections = ini.entrySet();
        for (Map.Entry<String, Section> s : sections) {
            if (s.getValue().get(entity.getOptionName()[0]).equals(entity.getObjectValue()[0])) {
                return s.getKey();
            }
        }
        return entity.getKey().toString();
    }
}
