package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;

@WebFilter(value = "/*", dispatcherTypes = DispatcherType.ERROR)
public class ErrorFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Throwable throwable = (Throwable) req.getAttribute(ERROR_EXCEPTION);

        if(res.getStatus() >= 500){
            log.error("Abonent: {} called Error 500: ", getAbonentId(req).getValue(), throwable);
            System.out.println("error");
        }else {
            log.warn("Abonent: {} called Exception code: {}", getAbonentId(req).getValue(), res.getStatus());
            System.out.println("warning");

        }

        filterChain.doFilter(req, res);
    }
}
