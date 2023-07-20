package com.fiatalis;

import com.fiatalis.listners.CommandPasser;
import com.fiatalis.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static Utils utils = new Utils();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list;
        CommandPasser commandPasser = new CommandPasser();
        while (true) {
            addPrefix();
            list = utils.parsing(reader.readLine());
            try {
                commandPasser.readerCommand(list.get(0));
            } catch (IllegalArgumentException e) {
                System.out.println("Команда не наедена");
            }
        }
    }


    private static void addPrefix() {
        System.out.print(">> ");
    }
}