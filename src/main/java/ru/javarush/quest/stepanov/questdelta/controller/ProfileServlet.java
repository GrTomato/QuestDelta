package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.exception.NoUserFoundException;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.MessageContainer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);

    @Override
    public void init() throws ServletException {
        List<UserRole> rolesAvailable = Arrays.stream(UserRole
                        .values())
                .filter(userRole -> userRole != UserService.getVisitorUser().getRole())
                .collect(Collectors.toList());
        getServletContext().setAttribute("roles", rolesAvailable);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);

        String password = formData.getParameter("user_password");
        String passwordRepeat = formData.getParameter("user_password_repeat");

        if (!Objects.equals(password, passwordRepeat)){
            req.setAttribute("message", MessageContainer.NOT_SAME_PASSWORD);
        } else {
            Optional<UserDTO> currentUser = userService.getUser(req.getSession());
            if (currentUser.isPresent()){
                Optional<UserDTO> updatedUserDTO = userService.updateById(currentUser.get().getId(), formData);
                updatedUserDTO.ifPresent(userDTO -> userService.setUser(req.getSession(false), userDTO));
            } else {
                throw new NoUserFoundException("No user found while editing profile data!");
            }
        }
        Jsp.forward(req, resp, "profile");
    }
}
