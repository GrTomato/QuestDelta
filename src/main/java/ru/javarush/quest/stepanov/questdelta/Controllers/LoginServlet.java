package ru.javarush.quest.stepanov.questdelta.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.Entity.User;
import ru.javarush.quest.stepanov.questdelta.Services.UserService;
import ru.javarush.quest.stepanov.questdelta.Utils.Jsp;
import ru.javarush.quest.stepanov.questdelta.Utils.MessageContainer;
import ru.javarush.quest.stepanov.questdelta.Utils.URLContainer;

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

        // прописать сценарий ошибок, когда оба поля пустые
        Optional<User> user = userService.getUserByLogin(req.getParameter("login"));
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            Jsp.redirect(resp, "/");
        } else {
            req.setAttribute("message", MessageContainer.USER_NOT_FOUND);
            Jsp.forward(req, resp, "login");
        }
    }
}
