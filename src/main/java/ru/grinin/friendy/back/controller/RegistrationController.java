package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.mapper.RequestToProfileRegistrationMapper;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.service.api.AbstractRegistrationService;
import ru.grinin.friendy.back.service.imp.RegistrationService;
import ru.grinin.friendy.back.util.AbonentIdGetter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {


    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final AbstractRegistrationService registrationService = RegistrationService.getINSTANCE();

    private final RequestToProfileRegistrationMapper registrationMapper = RequestToProfileRegistrationMapper.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("email") != null && !req.getParameter("email").isBlank()) {
            log.info("Abonent {} with email: {}, started filling out the questionnaire", getAbonentId(req).getValue(), req.getParameter("email"));
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            req.getRequestDispatcher("WEB-INF/jsp/registration-step-2.jsp").forward(req, resp);
        } else {
            log.info("Abonent {} starts registration new profile", getAbonentId(req).getValue());
            req.setAttribute("profile", new ProfileRegistrationDto());
            req.getRequestDispatcher("WEB-INF/jsp/registration-step-1.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileRegistrationDto dto = registrationMapper.mapTo(req);
        log.info("Abonent {} create new profile with email: {}", getAbonentId(req).getValue(), dto.getEmail());
        try {
            UUID id = registrationService.save(dto);
            log.debug("Abonent {} Profile with email: {}, received id = {}", getAbonentId(req).getValue(), dto.getEmail(), id);
            resp.sendRedirect(String.format("/profile?id=%s", id));
        } catch (EmailCollisionException e) {
            resp.setStatus(SC_BAD_REQUEST);
        }

    }
}
