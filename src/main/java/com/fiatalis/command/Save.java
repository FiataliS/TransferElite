package com.fiatalis.command;

import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

public class Save extends CommandRun {
    public Save(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда сохранит настройки в директории программы.");
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.OPT) {
            getOpt();
        }
    }

    private void getOpt() {
        ConfigUtils.getInstance().setGetIsDir(true);
        ConfigUtils.getInstance().iniWrite();
        ConfigUtils.getInstance().recordUser();
        ConfigUtils.getInstance().recordDir();
        ConfigUtils.getInstance().recordConnect();
        ConfigUtils.getInstance().recordServer();
    }
}
