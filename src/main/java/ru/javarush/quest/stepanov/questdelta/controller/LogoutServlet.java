package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.stepanov.questdelta.exception.NoValidSessionException;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;

@WebServlet(URLContainer.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession currentSession = req.getSession(false);

        if (currentSession != null){
            currentSession.invalidate();

            req.getSession().setAttribute("user", UserService.getVisitorUser());
            Jsp.redirect(resp, "/");
        } else {
            throw new NoValidSessionException("No valid session to invalidate during logout procedure.");
        }
    }
}
