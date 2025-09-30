package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ru.grinin.friendy.back.controller.api.Controller;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.service.imp.ProfileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileController extends HttpServlet implements Controller {


    private final ProfileService service = ProfileService.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response;
        if (req.getParameter("id") != null && req.getParameter("id").matches("........-....-....-....-............")) {
            response = findById(UUID.fromString(req.getParameter("id")));

        } else {
            response = findAll();
        }
        resp.setContentType("text/plain");
        resp.getWriter().write(response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(save(getBody(req).toString()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        update(getBody(req));
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(delete(UUID.fromString(req.getParameter("id"))));
    }

    public String work(String request) {
        String response = "response";
        return response;
    }

    public String save(String request) {

        String[] params = request.split(", ");
        if (params.length != 4) return "Bad request";
        Profile profile = new Profile();
        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);
        service.save(profile);


        return profile.toString();
    }

    public String findById(UUID id) {
        if (service.findById(id).isEmpty()) return "Not profile";
        return service.findById(id).get().toString();
    }

    public String update(String request) {
        String[] params = request.split(", ");
        if (params.length != 5) return "Bad request";
        Profile profile = new Profile();

        if (findById(UUID.fromString(params[0])).isEmpty()) return "Not profile";

        profile.setId(UUID.fromString(params[0]));
        profile.setEmail(params[1]);
        profile.setName(params[2]);
        profile.setSurname(params[3]);
        profile.setAbout(params[4]);
        service.update(profile.getId(), profile);

        return "successful";
    }

    public String delete(UUID id) {
        if (service.delete(id)) return "successful";
        return "fail";
    }

    public String findAll() {
        return service.findAll().toString();
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
}
