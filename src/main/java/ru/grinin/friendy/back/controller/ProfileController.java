package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.exception.EmailCollisionException;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.mapper.RequestToProfilePutMapper;
import ru.grinin.friendy.back.mapper.RequestToProfileStatusDtoMapper;
import ru.grinin.friendy.back.service.imp.ProfileService;
import ru.grinin.friendy.back.util.AbonentIdGetter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService service = ProfileService.getINSTANCE();

    private final RequestToProfilePutMapper requestMapper = RequestToProfilePutMapper.getINSTANCE();

    private final RequestToProfileStatusDtoMapper statusDtoMapper = RequestToProfileStatusDtoMapper.getINSTANCE();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        String forwardUri = "/notFound";

        if (id != null) {
            log.info("Abonent: {} requested profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), id);
            Optional<ProfileGetDto> optional = service.findById(UUID.fromString(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                forwardUri = "/WEB-INF/jsp/profile.jsp";
            }
        } else {
            log.info("Abonent: {} requested profiles", AbonentIdGetter.getAbonentId(req).getValue());
            req.setAttribute("profiles", service.findAll());
            forwardUri = "/WEB-INF/jsp/profiles.jsp";
        }


        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("status") != null) {
            ProfileStatusDto profileDto = statusDtoMapper.mapTo(req);
            try {
                log.info("Abonent: {} change status in profile with id {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.id());
                service.updateStatus(profileDto);
                log.info("Abonent: {} update profile {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.id());
                resp.sendRedirect("/profile");
            } catch (ProfileNotFoundException e) {
                resp.sendError(SC_NOT_FOUND);
            }
        } else {

            ProfilePutDto profileDto = requestMapper.mapTo(req);
            String sId = req.getParameter("id");
            log.info("Abonent: {} change profile with id {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.getId());
            UUID id = UUID.fromString(sId);
            profileDto.setId(id);
            try {
                service.update(profileDto);
                log.info("Abonent: {} update profile {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.getId());
                resp.sendRedirect(String.format("/profile?id=%s", id));
            } catch (ProfileNotFoundException e) {
                resp.setStatus(SC_NOT_FOUND);
            } catch (EmailCollisionException e) {
                resp.setStatus(SC_BAD_REQUEST);
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        log.info("Abonent: {} wanted delete profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), sId);
        if (!sId.isBlank()) {
            service.delete(UUID.fromString(sId));
        }
        log.info("Abonent: {} deleted profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), sId);
        resp.sendRedirect("/registration");
    }

}
