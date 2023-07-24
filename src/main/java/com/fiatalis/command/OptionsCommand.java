package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.EntityEnum;
import com.fiatalis.entity.MyAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

public class OptionsCommand extends CommandsRun {
    ConfigUtils configUtils = new ConfigUtils();

    public OptionsCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Это команда показывает свойства файла настроек: " + configUtils.getINI_FILE());
    }

    @Override
    void run() {
        User user = (User) configUtils.getEntity(EntityEnum.USER);
        MyAddress myAddress = (MyAddress) configUtils.getEntity(EntityEnum.MY_ADDRESS);
        ConnectAddress connectAddress = (ConnectAddress) configUtils.getEntity(EntityEnum.CONNECT_ADDRESS);
        Utils.printConsole(user + "\n" + connectAddress + "\n" + myAddress);
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
