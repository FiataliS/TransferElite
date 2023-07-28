package com.fiatalis.command;

import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

public class SaveIniCommand extends CommandsRun {
    public SaveIniCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда сохранит настройки в директории программы.");
    }

    @Override
    void run() {
        ConfigUtils.getInstance().setGetIsDir(true);
        ConfigUtils.getInstance().iniWrite();
        ConfigUtils.getInstance().recordUser();
        ConfigUtils.getInstance().recordDir();
        ConfigUtils.getInstance().recordConnect();
        ConfigUtils.getInstance().recordServer();
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
