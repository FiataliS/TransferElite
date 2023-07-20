package com.fiatalis.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public List<String> parsing(String string) {return Stream.of(string.split(" ")).collect(Collectors.toList());
    }
}
