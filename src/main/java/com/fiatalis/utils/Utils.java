package com.fiatalis.utils;

public class Utils {

    public static void addPrefix() {
        System.out.print(">> ");
    }

    public static void printConsole(String result, boolean isLn) {
        if (isLn) {
            System.out.println(result);
        } else {
            System.out.print(result + " ");
        }
    }

}
