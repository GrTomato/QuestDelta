package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.exception.NoValidSessionException;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.MessageContainer;
import ru.javarush.quest.stepanov.questdelta.util.SessionParser;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormData formData = FormData.of(req);
        Optional<UserDTO> user = userService.getUser(formData);
        if (user.isPresent()){
            HttpSession currentSession = req.getSession(false);

            if (currentSession != null){
                currentSession.invalidate();

                SessionParser.setSessionUser(req.getSession(), user.get());
                Jsp.redirect(resp, "/");
            } else {
                throw new NoValidSessionException("No session to invalidate during login procedure.");
            }
        } else {
            req.setAttribute("message", MessageContainer.USER_NOT_FOUND);
            Jsp.forward(req, resp, "login");
        }
    }
}
