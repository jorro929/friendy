package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class HiddenHttpMethodFilter implements Filter {

    public static final String METHOD_PARAM = "_method";


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String paramValue = request.getParameter(METHOD_PARAM);

        if ("POST".equals(request.getMethod()) && paramValue != null && !paramValue.isBlank()) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        if(context.getAttribute("genders") == null){
            context.setAttribute("genders", Gender.values());
        }
        if(context.getAttribute("status") == null){
            context.setAttribute("status", ProfileStatus.values());
        }
    }


    @Override
    public void destroy() {

    }


    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }

}