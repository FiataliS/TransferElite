package com.fiatalis.utils;

import com.fiatalis.entity.*;
import org.ini4j.Wini;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigUtils {
    private Wini ini;
    private final String INI_FILE = "config/TransferEliteConfig.ini";
    private boolean getIsDir = checkFileIni();

    public ConfigUtils() {
        if (getIsDir) {
            iniWrite();
            extractUser();
            extractDirectory();
            extractConnect();
            extractServer();
            extractShell();
            extractLanguage();
        }
    }

    public void iniWrite() {
        try {
            ini = new Wini(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGetIsDir(boolean getIsDir) {
        this.getIsDir = getIsDir;
    }

    private static volatile ConfigUtils instance;

    public static ConfigUtils getInstance() {
        ConfigUtils localInstance = instance;
        if (localInstance == null) {
            synchronized (ConfigUtils.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConfigUtils();
                }
            }
        }
        return localInstance;
    }

    public void recordDir() {
        if (getIsDir) {
            ini.put("Directory", "name", Directory.getInstance().getName());
            updateIniFile();
        }
    }

    public void recordUser() {
        if (getIsDir) {
            ini.put("User", "name", User.getInstance().getName());
            ini.put("User", "pass", User.getInstance().getPassHash());
            updateIniFile();
        }
    }

    public void recordServer(){
        if (getIsDir) {
            ini.put("Server", "address", Server.getInstance().getName());
            ini.put("Server", "port", Server.getInstance().getPort());
            updateIniFile();
        }
    }

    public void recordConnect(){
        if (getIsDir) {
            ini.put("Connect", "address", Connect.getInstance().getName());
            ini.put("Connect", "port", Connect.getInstance().getPort());
            updateIniFile();
        }
    }

    public void recordShell(){
        if (getIsDir) {
            ini.put("Interface", "shell", Skin.getInstance().getSkin());
            updateIniFile();
        }
    }

    public void recordLanguage(){
        if (getIsDir) {
            ini.put("Interface", "language", Language.getInstance().getLanguage());
            updateIniFile();
        }
    }


    public void extractDirectory() {
        if (getIsDir) {
            String name = ini.get("Directory", "name");
            if (name != null) {
                Directory.getInstance().setName(name);
            }
        }
    }

    public void extractUser() {
        if (getIsDir) {
            String user  = ini.get("User", "name");
            String pass = ini.get("User", "pass");
            if (user != null && pass != null) {
                User.getInstance().setName(user);
                User.getInstance().setPassHash(pass);
            }
        }
    }

    public void extractServer() {
        if (getIsDir) {
            String address  = ini.get("Server", "address");
            String port = ini.get("Server", "port");
            if (address != null && port != null) {
                Server.getInstance().setName(address);
                Server.getInstance().setPort(port);
            }
        }
    }

    public void extractConnect() {
        if (getIsDir) {
            String address  = ini.get("Connect", "address");
            String port = ini.get("Connect", "port");
            if (address != null && port != null) {
                Connect.getInstance().setName(address);
                Connect.getInstance().setPort(port);
            }
        }
    }

    public void extractLanguage() {
        if (getIsDir) {
            String language = ini.get("Interface", "language");
            if (language != null) {
                Language.getInstance().setLanguage(language);
            }
        }
    }

    public void extractShell() {
        if (getIsDir) {
            Boolean shell = Boolean.valueOf(ini.get("Interface", "shell"));
            if (shell != null) {
                Skin.getInstance().setSkin(shell);
            }
        }
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
        if (!checkFileIni()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private boolean checkFileIni() {
        File file = new File(INI_FILE);
        if (!file.exists()) {
            return false;
        }
        return true;
    }
}
