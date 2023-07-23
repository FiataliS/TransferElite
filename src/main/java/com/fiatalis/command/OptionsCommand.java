package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.EntityEnum;
import com.fiatalis.entity.MyAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.ConfigBuilding;
import com.fiatalis.utils.Utils;

public class OptionsCommand extends CommandsRun {
    ConfigBuilding configBuilding;

    public OptionsCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    void help() {
        Utils.printConsole("Это команда показывает свойства файла настроек: " + configBuilding.getINI_FILE());
    }

    @Override
    void run() {
        configBuilding = new ConfigBuilding();
        User user = (User) configBuilding.getEntity(EntityEnum.USER);
        MyAddress myAddress = (MyAddress) configBuilding.getEntity(EntityEnum.MY_ADDRESS);
        ConnectAddress connectAddress = (ConnectAddress) configBuilding.getEntity(EntityEnum.CONNECT_ADDRESS);
        Utils.printConsole(user + "\n" + connectAddress + "\n" + myAddress);
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
