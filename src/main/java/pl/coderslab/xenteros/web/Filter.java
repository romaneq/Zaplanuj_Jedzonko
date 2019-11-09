package pl.coderslab.xenteros.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/app/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        boolean isLogged;
        if (session.getAttribute("login") == null) {
            isLogged = false;
        } else {
            isLogged = (boolean) session.getAttribute("login");
        }

        if (isLogged) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
