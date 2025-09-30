package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ForwardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("hello") != null) {
            req.getRequestDispatcher("/hello").forward(req, resp);
        } else if(req.getParameter("profile") != null) {
            req.getRequestDispatcher("/profile").forward(req, resp);
        }else {
            resp.sendRedirect("https://ya.ru");
        }

    }
}
