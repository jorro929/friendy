package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.mapper.RequestToProfileRegistrationMapper;
import ru.grinin.friendy.back.service.api.RegistrationService;
import ru.grinin.friendy.back.service.imp.StandardRegistrationService;

import java.io.IOException;
import java.util.UUID;

import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;
import static ru.grinin.friendy.back.util.StringUtils.isBlank;

@WebServlet("/registration")
@Slf4j
public class RegistrationController extends HttpServlet {


    private final RegistrationService registrationService = StandardRegistrationService.getINSTANCE();

    private final RequestToProfileRegistrationMapper registrationMapper = RequestToProfileRegistrationMapper.getINSTANCE();

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("Init controller");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getSession().getAttribute("profile") != null && !isBlank(req.getParameter("email"))) {
            log.info("Abonent {} with email: {}, started filling out the questionnaire", getAbonentId(req).getValue(), req.getParameter("email"));
            ProfileRegistrationDto dto = (ProfileRegistrationDto) req.getSession().getAttribute("profile");
            dto.setEmail(req.getParameter("email") );
            dto.setPassword(req.getParameter("password") );
            req.getSession().setAttribute("profile", dto);

            req.getRequestDispatcher("WEB-INF/jsp/registration-step-2.jsp").forward(req, resp);
        } else {
            log.info("Abonent {} starts registration new profile", getAbonentId(req).getValue());
            req.getSession().setAttribute("profile", new ProfileRegistrationDto());
            req.getRequestDispatcher("WEB-INF/jsp/registration-step-1.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileRegistrationDto dto = registrationMapper.mapTo(req);
        req.getSession().setAttribute("profile", null);
        req.getSession().setAttribute("errors", null);

        log.info("Abonent {} create new profile with email: {}", getAbonentId(req).getValue(), dto.getEmail());
        try {
            UUID id = registrationService.save(dto);
            log.debug("Abonent {} Profile with email: {}, received id = {}", getAbonentId(req).getValue(), dto.getEmail(), id);
            resp.sendRedirect(String.format("/profile?id=%s", id));
        } catch (EmailCollisionException e) {
            req.getSession().setAttribute("errors", e.getMessage());
            log.warn("Abonent {} selected an already busy email address", getAbonentId(req).getValue());
            resp.sendRedirect("/registration");
        }catch (ValidException e){
            req.getSession().setAttribute("errors", e.getMessage());
            log.warn("Abonent {} make not valid profile", getAbonentId(req).getValue());
            resp.sendRedirect("/registration");
        }

    }
}
