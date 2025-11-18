package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Slf4j

@WebFilter("/*")
public class AbonentFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[]{};
        Optional<Cookie> abonentId = Arrays.stream(cookies).filter(cookie -> "abonentId".equals(cookie.getName())).findFirst();

        if(abonentId.isEmpty()){
            UUID id = UUID.randomUUID();
            log.info("New abonent: {}", id);
            Cookie cookie = new Cookie("abonentId", id.toString());
            res.addCookie(cookie);
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

}
