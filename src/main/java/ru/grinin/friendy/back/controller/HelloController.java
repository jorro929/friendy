package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grinin.friendy.back.controller.api.Controller;
import ru.grinin.friendy.back.service.imp.HelloService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@WebServlet("/hello")
public class HelloController extends HttpServlet {

    private final HelloService service = HelloService.getService();
    private  String servletName;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletName = config.getServletName();
        System.out.println("Init servlet " + servletName);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "get";

        if (req.getParameter("hello") != null) {
            response = service.findById((req.getParameter("hello")));
        } else {
            response = service.findAll();
        }
        resp.setContentType("text/plain");
        resp.getWriter().write(response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(service.save(getBody(req)));
    }

    private String getBody(HttpServletRequest req) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = req.getReader();
        ) {
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(service.update(getBody(req)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(service.delete(req.getParameter("hello")));
    }

    @Override
    public void destroy() {
        System.out.println("Destroy servlet " + servletName);
    }
}
