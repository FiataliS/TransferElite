package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

public class OptionsCommand extends CommandsRun {
    ConfigUtils configUtils = new ConfigUtils();

    public OptionsCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда показывает свойства файла настроек: " + configUtils.getINI_FILE());
    }

    @Override
    public void run() {
        User user = new User();
        ServerAddress serverAddress = new ServerAddress();
        ConnectAddress connectAddress = new ConnectAddress();
        Utils.printConsole(user.getEntity() + "\n" + connectAddress.getEntity() + "\n" + serverAddress.getEntity());
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
