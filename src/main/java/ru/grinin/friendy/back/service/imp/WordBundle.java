package ru.grinin.friendy.back.service.imp;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WordBundle {

    private final ResourceBundle resourceBundle;

    public WordBundle(String lang) {

        Locale locale = Locale.of("en");

        if ("ru".equals(lang)) {
            locale = Locale.of("ru");
        }else if ("fr".equals(lang)) {
            locale = Locale.of("fr");
        }

        resourceBundle = ResourceBundle.getBundle("words", locale);

    }

    public String getWord(String key) {

        String result;
        try {
            result = resourceBundle.getString(key.toLowerCase());
        }catch (ClassCastException | MissingResourceException e){
            result = key;
        }catch (Exception e){
            result = "* empty *";
        }
        return result;
    }

}
