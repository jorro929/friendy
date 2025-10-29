package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.controller.filter.ErrorFilter;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileRegistrationDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.mapper.ProfileGetToPutMapper;
import ru.grinin.friendy.back.mapper.RequestToProfilePutMapper;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.service.api.AbstractProfileService;
import ru.grinin.friendy.back.service.imp.ProfileService;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;

@WebServlet("/email")
@Slf4j
public class EmailController extends HttpServlet {


    private final AbstractProfileService profileService = ProfileService.getINSTANCE();

    private final ProfileGetToPutMapper mapper = ProfileGetToPutMapper.getINSTANCE();

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

        Optional<ProfileGetDto> optional = profileService.findById(UUID.fromString(req.getParameter("id")));
        if (optional.isPresent()) {
            ProfilePutDto dto = mapper.mapTo(optional.get());
            dto.setEmail(req.getParameter("email"));
            dto.setId(UUID.fromString(req.getParameter("id")));
            try {
                profileService.update(dto);
                log.info("Abonent: {} changed email in profile with id {}", getAbonentId(req).getValue(), dto.getId());
                resp.sendRedirect(String.format("/profile?id=%s", dto.getId()));
            } catch (EmailCollisionException e) {
                resp.sendError(SC_BAD_REQUEST);
            } catch (ProfileNotFoundException e) {
                resp.sendError(SC_NOT_FOUND);
            }
        } else {
            resp.sendError(SC_NOT_FOUND);
        }
    }
}
