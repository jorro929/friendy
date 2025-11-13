package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfileUpdateEmailDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.mapper.RequestToProfileEmailDtoMapper;
import ru.grinin.friendy.back.service.api.ProfileService;
import ru.grinin.friendy.back.service.imp.StandardProfileService;


import java.io.IOException;
import java.util.*;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;

@WebServlet("/email")
@Slf4j
public class EmailController extends HttpServlet {


    private final ProfileService profileService = StandardProfileService.getINSTANCE();

    private final RequestToProfileEmailDtoMapper emailMapper = RequestToProfileEmailDtoMapper.getINSTANCE();


    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("Init controller");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sId = req.getParameter("id");
        String forwardUri = null;
        if (sId != null) {
            log.info("Abonent: {} want change email by profile with id {}", getAbonentId(req).getValue(), sId);
            Optional<ProfileGetDto> optProfileDto = profileService.findById(UUID.fromString(sId));
            if (optProfileDto.isPresent()) {
                req.setAttribute("profile", optProfileDto.get());
                forwardUri = "/WEB-INF/jsp/email.jsp";
            }
        }
        if (forwardUri == null) {
            resp.sendError(SC_NOT_FOUND);
        } else {
            req.getRequestDispatcher(forwardUri).forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProfileUpdateEmailDto dto = emailMapper.mapTo(req);
        try {
            profileService.updateEmail(dto);
            log.info("Abonent: {} changed email in profile with id {}", getAbonentId(req).getValue(), dto.id());
            resp.sendRedirect(String.format("/profile?id=%s", dto.id()));
        } catch (EmailCollisionException e) {
            req.getSession().setAttribute("errors", e.getMessage());
            log.warn("Abonent {} selected an already busy email address", getAbonentId(req).getValue());
            resp.sendRedirect("/profile?id=" + req.getParameter("id"));
        } catch (ProfileNotFoundException e) {
            resp.sendError(SC_NOT_FOUND);
        } catch (ValidException e) {
            req.getSession().setAttribute("errors", e.getMessage());
            log.warn("Abonent {} make not valid profile", getAbonentId(req).getValue());
            resp.sendRedirect("/registration");
        }
    }
}
