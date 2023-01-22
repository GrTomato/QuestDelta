package ru.javarush.quest.stepanov.questdelta.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.stepanov.questdelta.Entity.Quest;
import ru.javarush.quest.stepanov.questdelta.Entity.User;
import ru.javarush.quest.stepanov.questdelta.Entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.Services.QuestService;
import ru.javarush.quest.stepanov.questdelta.Utils.Jsp;
import ru.javarush.quest.stepanov.questdelta.Utils.RepositoryLoader;
import ru.javarush.quest.stepanov.questdelta.Utils.URLContainer;

import java.io.IOException;
import java.util.Collection;

@WebServlet(URLContainer.START)
public class StartPageServlet extends HttpServlet {

    private QuestService questService = QuestService.INSTANCE;

    @Override
    public void init() throws ServletException {
        super.init();
        RepositoryLoader.load();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (sessionUserEmpty(req)){
            req.getSession().setAttribute("user", User.with().role(UserRole.VISITOR).build());
        }

        Collection<Quest> quests = questService.getAll();
        req.setAttribute("quests", quests);
        Jsp.forward(req, resp, "quests.jsp");
    }

    private boolean sessionUserEmpty(HttpServletRequest req){
        return req.getSession().getAttribute("user") == null;
    }

}
