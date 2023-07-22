package com.fiatalis.utils;

import com.fiatalis.entity.Address;
import com.fiatalis.entity.Entity;
import com.fiatalis.entity.EntityEnum;
import com.fiatalis.entity.User;
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
        addNew(new User("Антон", "3asdasd2"));
        addNew(new User("Енот", "3vbngffg2"));
        System.out.println(ini);
    }

    public void addNew(Entity entity) {
        String sectionName = getSectionName(entity);
        for (int i = 0; i < entity.getOptionName().length; i++) {
            ini.put(sectionName, entity.getOptionName()[i], entity.getObjectValue()[i]);
        }
//        if (entity instanceof User) {
//            ini.put(entity.getKey().name(), "user", entity.getOptionName());
//            ini.put(getSectionName(EntityEnum.USER), "pass", ((User) entity).getPassword());
//        } else if (entity instanceof Address) {
//            ini.put(getSectionName(EntityEnum.ADDRESS), "address", ((Address) entity).getName());
//            ini.put(getSectionName(EntityEnum.ADDRESS), "port", ((Address) entity).getPort());
//        }
        updateIniFile();
    }


    public void deleteSection() {

    }


    void updateIniFile() {
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
        return entity.getKey().name() + sections.size();
    }

    private boolean checkEntity(String name) {
        Set<Map.Entry<String, Section>> sections = ini.entrySet();
        return sections.stream().anyMatch(s -> s.getValue().getName().equals(name));
    }

}
