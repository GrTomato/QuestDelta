package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.exception.NoUserFoundException;
import ru.javarush.quest.stepanov.questdelta.service.GameService;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.MessageContainer;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet(URLContainer.GAMES)
public class GamesServlet extends HttpServlet {

    private final GameService gameService = Winter.getBean(GameService.class);
    private final UserService userService = Winter.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<UserDTO> currentUser = userService.getUser(req.getSession());
        if (currentUser.isPresent()){
            Collection<GameDTO> userGames = gameService.getAllByUser(currentUser.get().getId());
            if (userGames.size() > 0){
                req.setAttribute("games", userGames);
                Jsp.forward(req, resp, "games");
            } else {
                req.setAttribute("message", MessageContainer.NO_GAMES_FOUND);
                Jsp.forward(req, resp, "games");
            }
        } else {
            throw new NoUserFoundException("GAMES_LISTING_ERROR: No user was found.");
        }
    }
}
