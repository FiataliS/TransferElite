package com.fiatalis;

import com.fiatalis.handler.CommandHandler;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list;
        CommandHandler commandHandler;
        while (true) {
            Utils.addPrefix();
            list = Utils.parsing(reader.readLine());
            commandHandler = new CommandHandler(list);
            try {
                commandHandler.commandDefinition();
            } catch (IllegalArgumentException e) {
                System.out.println("Команда не наедена");
            }
        }
    }

}