package com.fiatalis.utils;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parsing(String string) {
        List<String> list = new ArrayList<>();
        String[] arr = string.split(" ");
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
