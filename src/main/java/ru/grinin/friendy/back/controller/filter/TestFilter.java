package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(urlPatterns = "/profile",
initParams = @WebInitParam(name = "firsrt", value = "value", description = "sdgffdsg"))
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init TEST FILTER");
        System.out.println(filterConfig.getInitParameter("firsrt"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do something before servlet");

        System.out.println(servletRequest.getProtocolRequestId());;

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("do something after servlet");
    }
}
