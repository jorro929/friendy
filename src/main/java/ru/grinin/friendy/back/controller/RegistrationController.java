package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.mapper.RequestToProfileRegistrationMapper;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.service.api.AbstractRegistrationService;
import ru.grinin.friendy.back.service.imp.RegistrationService;

import java.io.IOException;
import java.time.LocalDate;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private final AbstractRegistrationService registrationService = RegistrationService.getINSTANCE();

    private final RequestToProfileRegistrationMapper registrationMapper = RequestToProfileRegistrationMapper.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("email") != null) {
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            req.getRequestDispatcher("WEB-INF/jsp/registration-step-2.jsp").forward(req, resp);
        } else {
            req.setAttribute("profile", new ProfileRegistrationDto());
            req.getRequestDispatcher("WEB-INF/jsp/registration-step-1.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileRegistrationDto dto = registrationMapper.mapTo(req);
        try {
            resp.sendRedirect(String.format("/profile?id=%s", registrationService.save(dto)));
        } catch (EmailCollisionException e) {
            resp.setStatus(SC_BAD_REQUEST);
        }

    }
}
