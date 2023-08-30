package com.fiatalis.client.command;

import com.fiatalis.entity.*;
import com.fiatalis.entity.Connect;
import com.fiatalis.utils.Utils;

public class Opt extends CommandRun {

    public Opt(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void optionsHandler() {
        opt();
    }

    private void opt() {
        Utils.printConsole(User.getInstance() + "\n" + Connect.getInstance() + "\n" + Server.getInstance() + "\n" + Directory.getInstance() + "\n" + Skin.getInstance());
    }
}
