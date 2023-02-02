package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.GameState;
import ru.javarush.quest.stepanov.questdelta.exception.NoUserFoundException;
import ru.javarush.quest.stepanov.questdelta.service.GameService;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet("/stats")
public class StatisticsServlet extends HttpServlet {

    private final GameService gameService = Winter.getBean(GameService.class);
    private final UserService userService = Winter.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Optional<UserDTO> currentUser = userService.getUser(req.getSession());
        if (currentUser.isPresent()){
            Collection<GameDTO> allByUser = gameService.getAllByUser(currentUser.get().getId());
            req.setAttribute("totalGames", allByUser.size());
            req.setAttribute("finishedGames", allByUser.stream()
                    .filter(game -> game.getGameState() != GameState.PROGRESS)
                    .toList().size()
            );
        } else {
            throw new NoUserFoundException("No use found during statistics gathering.");
        }


        Jsp.forward(req, resp, "statistics");
    }
}
