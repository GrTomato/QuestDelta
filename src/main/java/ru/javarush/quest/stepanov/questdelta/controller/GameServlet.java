package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.exception.NoGameFoundException;
import ru.javarush.quest.stepanov.questdelta.exception.NoQuestFoundException;
import ru.javarush.quest.stepanov.questdelta.exception.NoQuestionFoundException;
import ru.javarush.quest.stepanov.questdelta.exception.NoUserFoundException;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.service.GameService;
import ru.javarush.quest.stepanov.questdelta.service.QuestService;
import ru.javarush.quest.stepanov.questdelta.service.QuestionService;
import ru.javarush.quest.stepanov.questdelta.service.UserService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.GAME)
public class GameServlet extends HttpServlet {

    private final GameService gameService = Winter.getBean(GameService.class);
    private final UserService userService = Winter.getBean(UserService.class);
    private final QuestionService questionService = Winter.getBean(QuestionService.class);
    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FormData formData = FormData.of(req);
        Optional<QuestDTO> quest = questService.getQuest(formData);
        Optional<UserDTO> currentUser = userService.getUser(req.getSession());

        if (currentUser.isPresent()){
            if (quest.isPresent()){
                Optional<GameDTO> game = gameService.getGame(
                        formData,
                        currentUser.get().getId(),
                        quest.get().getId()
                );

                if (game.isPresent()){
                    req.setAttribute("game", game.get());
                    Jsp.forward(req, resp, "game");
                } else {
                    Optional<GameDTO> newGame = gameService.createNewGame(quest.get().getId(), currentUser.get().getId());
                    req.setAttribute("game", newGame.get()); // as we create new game we should not write check
                    Jsp.forward(req, resp, "game");
                }
            } else {
                throw new NoQuestionFoundException("GET_GAME_ERROR: No quest was found.");
            }
        } else {
            throw new NoUserFoundException("GET_GAME_ERROR: No user was found.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        Optional<QuestDTO> quest = questService.getQuest(formData);
        Optional<UserDTO> currentUser = userService.getUser(req.getSession());
        Optional<QuestionDTO> nextQuestion = questionService.getQuestion(formData);

        if (currentUser.isPresent()){
            if (quest.isPresent()){
                if (nextQuestion.isPresent()){
                    Optional<GameDTO> currentGame = gameService.getGame(formData, currentUser.get().getId(), quest.get().getId());
                    if (currentGame.isPresent()){
                        currentGame = gameService.updateGameProgress(currentGame.get().getId(), nextQuestion.get().getId());
                        req.setAttribute("game", currentGame.get());
                        Jsp.forward(req, resp, "game");
                    } else {
                        throw new NoGameFoundException("UPDATE_GAME_ERROR: No game found while updating game progress.");
                    }
                } else {
                    throw new NoQuestionFoundException("UPDATE_GAME_ERROR: No question found while updating game progress.");
                }
            } else {
                  throw new NoQuestFoundException("UPDATE_GAME_ERROR: No quest found while updating game progress.");
            }
        } else {
            throw new NoUserFoundException("UPDATE_GAME_ERROR: No user found while updating game progress.");
        }
    }
}
