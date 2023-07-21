package com.fiatalis.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static List<String> parsing(String string) {return Stream.of(string.split(" ")).collect(Collectors.toList());}

    public static void addPrefix() {System.out.print(">> ");}

    public static void printConsole(String result) {System.out.println(result);}
}
