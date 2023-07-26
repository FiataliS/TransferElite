package com.fiatalis.command;

import com.fiatalis.Client;
import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.User;
import com.fiatalis.utils.Utils;

public class ConnectCommand extends CommandsRun {

    public ConnectCommand(Attribute attribute) {
        super(attribute);
    }

    @Override
    public void help() {
        Utils.printConsole("Это соединяется с сервером");
    }

    @Override
    public void run() {
        Client client = Client.getInstance();
        ConnectAddress connectAddress = (ConnectAddress) new ConnectAddress().getEntity();
        User user = (User) new User().getEntity();
        if (!client.isAuthorized()) {
            client.connect(connectAddress);
            client.authentication(user);
            return;
        }
        System.out.println("Уже подключены к адрессу: " + connectAddress.getName() + ":" + connectAddress.getPort());
    }

    @Override
    public void attributeHandler() {
        if (super.attribute.getAttribute().equals("help")) {
            help();
        }
    }
}
