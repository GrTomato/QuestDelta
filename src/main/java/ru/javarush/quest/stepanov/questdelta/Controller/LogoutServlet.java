package ru.javarush.quest.stepanov.questdelta.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.Entity.User;
import ru.javarush.quest.stepanov.questdelta.Entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.Utils.Jsp;
import ru.javarush.quest.stepanov.questdelta.Utils.URLContainer;

import java.io.IOException;

@WebServlet(URLContainer.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", User.with().role(UserRole.VISITOR).build());
        Jsp.redirect(resp, "/");
    }
}
