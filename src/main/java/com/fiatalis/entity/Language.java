package com.fiatalis.entity;

import lombok.Data;

import java.util.Locale;

@Data
public class Language {
    String language;

    private static volatile Language instance;
    {
        language = "eng";
    }

    public static Language getInstance() {
        Language localInstance = instance;
        if (localInstance == null) {
            synchronized (Language.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Language();
                }
            }
        }
        return localInstance;
    }

    public Locale getLocate(){
        return new Locale(getLanguage());
    }

    @Override
    public String toString() {
        return "Language: " + language;
    }
}
