package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.service.GameService;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.GAME)
public class GameServlet extends HttpServlet {

    private final GameService gameService = GameService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormData formData = FormData.of(req);

        UserDTO currentUser = userService.getUser(req.getSession());
        Optional<GameDTO> game = gameService.getGame(
                formData,
                currentUser.getId()
        );

        if (game.isPresent()){
            req.setAttribute("game", game.get());
            Jsp.forward(req, resp, "game");
        } else {
            throw new RuntimeException("No game DTO was found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // добавить постоянную проверку в game service, что есть такая пара Квест-Ответ в рамках текущей игры
        FormData formData = FormData.of(req);
        Optional<GameDTO> gameDTO = gameService.updateGameProgress(formData);

        if (gameDTO.isPresent()){
            req.setAttribute("game", gameDTO.get());
            Jsp.forward(req, resp, "game");
        } else {
            throw new RuntimeException("No game for game dto.");
        }

    }
}
