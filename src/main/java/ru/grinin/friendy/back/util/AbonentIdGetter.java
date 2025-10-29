package ru.grinin.friendy.back.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public  class AbonentIdGetter {

    public static Cookie getAbonentId(HttpServletRequest req){
        return Arrays.stream(req.getCookies()).filter(cookie1 -> "abonentId".equals(cookie1.getName())).findFirst().get();
    }
}
