package ru.grinin.friendy.back.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public final class AbonentIdGetter {

    private AbonentIdGetter(){}

    public static Cookie getAbonentId(HttpServletRequest req){
        return Arrays.stream(req.getCookies()).filter(cookie1 -> "abonentId".equals(cookie1.getName())).findFirst().get();
    }
}
