package com.fiatalis;

import com.fiatalis.utils.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static Parser parser = new Parser();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list;
        while (true) {
            addPrefix();
            list = parser.parsing(reader.readLine());
            System.out.println(list);
        }
    }


    private static void addPrefix() {
        System.out.print(">> ");
    }
}