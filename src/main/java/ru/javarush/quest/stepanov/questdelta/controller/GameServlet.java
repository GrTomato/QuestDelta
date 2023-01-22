package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.entity.Game;
import ru.javarush.quest.stepanov.questdelta.entity.Question;
import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.service.GameService;
import ru.javarush.quest.stepanov.questdelta.service.QuestionService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Optional;

@WebServlet(URLContainer.GAME)
public class GameServlet extends HttpServlet {

    private final GameService gameService = GameService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questId = req.getParameter("quest_id");
        Optional<String> instruction = Optional.ofNullable(req.getParameter("instruct"));

        User currentUser = (User) req.getSession().getAttribute("user");
        Game game = gameService.getGame(Long.parseLong(questId), currentUser.getId());

        if (instruction.isPresent()){
            if (instruction.get().equalsIgnoreCase("restart")) gameService.restartGame(game);
        }


        req.setAttribute("game", game);
        Jsp.forward(req, resp, "game");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String gameId = req.getParameter("id");
        String selectedAnswerNextQuestion = req.getParameter("selectedAnswerNextQuestion");

        Optional<Question> nextQuestion = questionService.getById(Long.parseLong(selectedAnswerNextQuestion));
        if (nextQuestion.isPresent()){
            Optional<Game> currentGame = gameService.getById(Long.parseLong(gameId));
            if (currentGame.isPresent()){
                currentGame.get().setLastRedirectedQuestion(nextQuestion.get());

                req.setAttribute("game", currentGame.get());
                Jsp.forward(req, resp, "game");
            } else {
                // ошибка для разработчика, не пользовательский сценарий
                throw new RuntimeException("no such game id");
            }
        } else {
            // сценарий финиша или ошибки
            throw new RuntimeException("no such question id");
        }


    }
}
