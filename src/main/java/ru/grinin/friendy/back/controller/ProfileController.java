package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grinin.friendy.back.model.Profile;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.service.imp.ProfileService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private final ProfileService service = ProfileService.getINSTANCE();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        String forwardUri = "/notFound";

        if (id != null) {
            Optional<Profile> optional = service.findById(UUID.fromString(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                forwardUri = "/WEB-INF/jsp/profile.jsp";
            }
        }


        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profile profile = getProfile(req);
        service.save(profile).getId();
        resp.sendRedirect(String.format("/profile?id=%s", profile.getId().toString()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profile profile = getProfile(req);
        String sId = req.getParameter("id");
        UUID id = UUID.fromString(sId);
        profile.setId(id);
        service.update(id, profile);
        resp.sendRedirect(String.format("/profile?id=%s", profile.getId().toString()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if(!sId.isBlank()){
            service.delete(UUID.fromString(sId));
        }
        resp.sendRedirect("/registration");
    }

    private Profile getProfile(HttpServletRequest req) {
        return new Profile(req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("email"),
                req.getParameter("about"),
                Gender.valueOf(req.getParameter("gender")));
    }
}
