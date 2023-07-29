package com.fiatalis.command;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.Directory;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.Utils;

public class Opt extends CommandRun {

    public Opt(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это команда показывает настройки программы");
    }

    @Override
    public void optionsHandler() {
        opt();
    }

    private void opt() {
        Utils.printConsole(User.getInstance() + "\n" + ConnectAddress.getInstance() + "\n" + ServerAddress.getInstance() + "\n" + Directory.getInstance());
    }
}
