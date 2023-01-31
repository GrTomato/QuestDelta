package ru.javarush.quest.stepanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.service.QuestService;
import ru.javarush.quest.stepanov.questdelta.util.Jsp;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;
import java.util.Collection;

@WebServlet(URLContainer.START)
public class StartPageServlet extends HttpServlet {

    private QuestService questService = QuestService.INSTANCE;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<QuestDTO> quests = questService.getAll();
        req.setAttribute("quests", quests);
        Jsp.forward(req, resp, "quests.jsp");
    }

}
