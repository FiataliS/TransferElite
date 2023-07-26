package com.fiatalis;

import com.fiatalis.handler.CommandHandler;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client client = new Client();
        CommandHandler commandHandler;
        while (true) {
            Utils.addPrefix();
            commandHandler = new CommandHandler(reader.readLine());
            commandHandler.commandDefinition();
        }
    }

}