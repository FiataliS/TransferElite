package com.fiatalis;

import com.fiatalis.entity.ConnectAddress;
import com.fiatalis.entity.Directory;
import com.fiatalis.entity.ServerAddress;
import com.fiatalis.entity.User;
import com.fiatalis.handler.CommandHandler;
import com.fiatalis.utils.ConfigUtils;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        init();
        CommandHandler commandHandler;
        while (true) {
            Utils.addPrefix();
            commandHandler = new CommandHandler(reader.readLine());
            commandHandler.commandDefinition();
        }
    }

    private static void init(){
        ConfigUtils.getInstance();
        User.getInstance();
        Directory.getInstance();
        ServerAddress.getInstance();
        ConnectAddress.getInstance();

        Client.getInstance();
        EchoServer.getInstance();
    }

}