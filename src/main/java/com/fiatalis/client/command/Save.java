package com.fiatalis.client.command;

import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

public class Save extends CommandRun {
    public Save(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        if (attribute.getOptions() == OptionsEnum.OPT) {
            saveOpt();
        }
    }

    private void saveOpt() {
        ConfigUtils.getInstance().setGetIsDir(true);
        ConfigUtils.getInstance().iniWrite();
        ConfigUtils.getInstance().recordUser();
        ConfigUtils.getInstance().recordDir();
        ConfigUtils.getInstance().recordConnect();
        ConfigUtils.getInstance().recordServer();
        ConfigUtils.getInstance().recordShell();
        ConfigUtils.getInstance().recordLanguage();
    }
}
