package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.util.AbonentIdGetter;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/lang")
@Slf4j
public class LangController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        Cookie cookie = new Cookie("lang", "en");
        log.info("Abonent: {} change language", AbonentIdGetter.getAbonentId(req).getValue());
        if ("ru".equals(lang)) {
            cookie.setValue("ru");
        } else if ("fr".equals(lang)) {
            cookie.setValue("fr");
        }
        resp.addCookie(cookie);

        String referer = req.getHeader("referer");

        resp.sendRedirect(referer);
    }
}
