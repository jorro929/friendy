package ru.grinin.friendy.back.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grinin.friendy.back.model.supportclass.Gender;
import ru.grinin.friendy.back.model.supportclass.ProfileStatus;

import java.io.IOException;
import java.util.Locale;

import static jakarta.servlet.DispatcherType.FORWARD;
import static jakarta.servlet.DispatcherType.REQUEST;
import static ru.grinin.friendy.back.util.AbonentIdGetter.getAbonentId;
import static ru.grinin.friendy.back.util.StringUtils.isBlank;

@Slf4j

@WebFilter(value = "/*", dispatcherTypes = {FORWARD, REQUEST})
public class HiddenHttpMethodFilter implements Filter {

    public static final String METHOD_PARAM = "_method";


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        if (request.getDispatcherType() == FORWARD && request instanceof HttpMethodRequestWrapper) {
            ((HttpMethodRequestWrapper) request).setMethod("GET");
        } else {
            String paramValue = request.getParameter(METHOD_PARAM);

            if ("POST".equals(request.getMethod()) && !isBlank(paramValue)) {
                String method = paramValue.toUpperCase(Locale.ENGLISH);
                request = new HttpMethodRequestWrapper(request, method);
            }
        }

        filterChain.doFilter(request, response);

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

    @Setter
    @Getter
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

    }

}