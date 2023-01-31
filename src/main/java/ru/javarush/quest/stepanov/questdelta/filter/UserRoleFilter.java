package ru.javarush.quest.stepanov.questdelta.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;

@WebFilter(filterName = "RoleSelector", value = {URLContainer.START, URLContainer.LOGIN, URLContainer.GAMES, URLContainer.GAME, URLContainer.LOGOUT, URLContainer.REGISTER})
public class UserRoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession currentSession = request.getSession();
        if (currentSession != null && currentSession.getAttribute("user") == null){
            currentSession.setAttribute("user", UserService.getVisitorUser());
        }

        chain.doFilter(req, res);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

