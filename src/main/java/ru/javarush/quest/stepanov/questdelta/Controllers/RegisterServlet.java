package ru.javarush.quest.stepanov.questdelta.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.Entity.User;
import ru.javarush.quest.stepanov.questdelta.Entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.Repository.UserRepository;
import ru.javarush.quest.stepanov.questdelta.Services.UserService;
import ru.javarush.quest.stepanov.questdelta.Utils.Jsp;
import ru.javarush.quest.stepanov.questdelta.Utils.MessageContainer;
import ru.javarush.quest.stepanov.questdelta.Utils.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.REGISTER)
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<User> user = userService.getUserByLogin(req.getParameter("login"));

        if (user.isPresent()){
            req.setAttribute("message", MessageContainer.USER_ALREADY_REGISTERED);
            Jsp.forward(req, resp, "register");
        } else {
            User newUser = User.with()
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .email(req.getParameter("email"))
                    .role(UserRole.PLAYER)
                    .build();
            userService.create(newUser);
        }

        Jsp.redirect(resp, "/");
    }
}
