package com.fiatalis;

import com.fiatalis.listners.CommandPasser;
import com.fiatalis.utils.ConfigBuilding;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ConfigBuilding t = new ConfigBuilding();



        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list;
        CommandPasser commandPasser = new CommandPasser();
        while (true) {
            Utils.addPrefix();
            list = Utils.parsing(reader.readLine());
            try {
                commandPasser.readerCommand(list.get(0));
            } catch (IllegalArgumentException e) {
                System.out.println("Команда не наедена");
            }
        }
    }

}