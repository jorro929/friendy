package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.dto.ProfileGetDto;
import ru.grinin.friendy.back.dto.ProfilePutDto;
import ru.grinin.friendy.back.dto.ProfileStatusDto;
import ru.grinin.friendy.back.exception.ProfileNotFoundException;
import ru.grinin.friendy.back.exception.ValidException;
import ru.grinin.friendy.back.mapper.RequestToProfilePutMapper;
import ru.grinin.friendy.back.mapper.RequestToProfileStatusDtoMapper;
import ru.grinin.friendy.back.service.api.ContentService;
import ru.grinin.friendy.back.service.api.ProfileService;
import ru.grinin.friendy.back.service.imp.StandardContentService;
import ru.grinin.friendy.back.service.imp.StandardProfileService;
import ru.grinin.friendy.back.util.AbonentIdGetter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;

@Slf4j
@WebServlet("/profile")
@MultipartConfig()
public class ProfileController extends HttpServlet {


    private final ProfileService profileService = StandardProfileService.getINSTANCE();


    private final RequestToProfilePutMapper requestMapper = RequestToProfilePutMapper.getINSTANCE();

    private final RequestToProfileStatusDtoMapper statusDtoMapper = RequestToProfileStatusDtoMapper.getINSTANCE();

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("Init controller");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        String forwardUri = "/notFound";

        if (id != null) {
            log.info("Abonent: {} requested profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), id);
            Optional<ProfileGetDto> optional = profileService.findById(UUID.fromString(id));
            if (optional.isPresent()) {
                req.setAttribute("profile", optional.get());
                forwardUri = "/WEB-INF/jsp/profile.jsp";
            }
        } else {
            log.info("Abonent: {} requested profiles", AbonentIdGetter.getAbonentId(req).getValue());
            req.setAttribute("profiles", profileService.findAll());
            forwardUri = "/WEB-INF/jsp/profiles.jsp";
        }


        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("errors", null);
        try {
            if (req.getParameter("status") != null) {
                updateStatus(req, resp);
            } else {
                updateProfile(req, resp);
            }
        } catch (ProfileNotFoundException e) {
            resp.setStatus(SC_NOT_FOUND);
        } catch (ValidException e) {
            req.getSession().setAttribute("errors", e.getMessage());
            log.warn("Abonent {} make not valid profile", getAbonentId(req).getValue());
            resp.sendRedirect(String.format("/profile?id=%s", req.getParameter("id")));
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        log.info("Abonent: {} wanted delete profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), sId);
        if (!sId.isBlank()) {
            try {
                profileService.delete(UUID.fromString(sId));
            } catch (ProfileNotFoundException e) {
                resp.setStatus(SC_NOT_FOUND);
            }
        }
        log.info("Abonent: {} deleted profile with id: {}", AbonentIdGetter.getAbonentId(req).getValue(), sId);
        resp.sendRedirect("/registration");
    }


    @SneakyThrows
    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws ProfileNotFoundException, ValidException {
        req.getPart("photo");
        ProfilePutDto profileDto = requestMapper.mapTo(req);
        String sId = req.getParameter("id");
        log.info("Abonent: {} change profile with id {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.getId());
        UUID id = UUID.fromString(sId);
        profileDto.setId(id);

        profileService.update(profileDto);
        log.info("Abonent: {} update profile {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.getId());
        resp.sendRedirect(String.format("/profile?id=%s", id));
    }

    @SneakyThrows
    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws ProfileNotFoundException, ValidException {
        ProfileStatusDto profileDto = statusDtoMapper.mapTo(req);
        if(profileDto.status() != null) {
        log.info("Abonent: {} change status in profile with id {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.id());
        profileService.updateStatus(profileDto);
        log.info("Abonent: {} update profile {}", AbonentIdGetter.getAbonentId(req).getValue(), profileDto.id());
        }
        resp.sendRedirect("/profile");
    }


}
