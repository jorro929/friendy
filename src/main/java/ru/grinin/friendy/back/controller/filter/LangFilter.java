package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grinin.friendy.back.service.imp.WordBundle;

import java.io.IOException;
import java.util.Arrays;


@WebFilter("/*")
public class LangFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[]{};

        String lang = Arrays.stream(cookies).filter(cookie -> "lang".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse("en");

        WordBundle wordBundle = new WordBundle(lang);

        req.setAttribute("wordBundle", wordBundle);


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
