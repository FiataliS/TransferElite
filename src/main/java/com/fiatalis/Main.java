package com.fiatalis;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Connect;
import com.fiatalis.entity.Directory;
import com.fiatalis.entity.Server;
import com.fiatalis.entity.User;
import com.fiatalis.client.CommandHandler;
import com.fiatalis.server.EchoServer;
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
        Server.getInstance();
        Connect.getInstance();

        Client.getInstance();
        EchoServer.getInstance();
    }

}