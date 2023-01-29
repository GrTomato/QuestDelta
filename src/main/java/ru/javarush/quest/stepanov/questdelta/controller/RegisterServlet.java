package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.MessageContainer;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.REGISTER)
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormData formData = FormData.of(req);
        Optional<UserDTO> foundUser = userService.getUser(formData);

        if (foundUser.isEmpty()){
            Optional<UserDTO> userDTO = userService.create(formData);
            if (userDTO.isPresent()){
                Jsp.redirect(resp, "/");
            } else {
                req.setAttribute("message", MessageContainer.UNEXPECTED_BEHAVIOUR);
                Jsp.forward(req, resp, "register");
            }
        } else {
            req.setAttribute("message", MessageContainer.USER_ALREADY_REGISTERED);
            Jsp.forward(req, resp, "register");
        }
    }
}
