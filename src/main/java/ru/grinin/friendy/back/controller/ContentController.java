package ru.grinin.friendy.back.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.grinin.friendy.back.service.api.ContentService;
import ru.grinin.friendy.back.service.imp.StandardContentService;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@Slf4j
@WebServlet("/content/*")
public class ContentController extends HttpServlet {

    private static final ContentService contentService = StandardContentService.getINSTANCE();


    @Override
    public void init(ServletConfig config) throws ServletException {
        log.debug("Content service is init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentPath = req.getRequestURI().replace("/content", "");
        log.info("Request photo with path: {}", contentPath);
        resp.setContentType("application/octet-stream");
        try {
            contentService.download(contentPath, resp.getOutputStream());
        }catch (IOException e){
            resp.sendError(SC_NOT_FOUND);
        }
    }
}
