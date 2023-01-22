package ru.javarush.quest.stepanov.questdelta.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Jsp {

    public void forward(HttpServletRequest req, HttpServletResponse resp, String jspPath) throws ServletException, IOException {
        String path = jspPath.contains(".jsp")
                ? "WEB-INF/%s".formatted(jspPath)
                : "WEB-INF/%s.jsp".formatted(jspPath);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    public void redirect(HttpServletResponse resp, String jspPath) throws IOException {
        resp.sendRedirect(jspPath);
    }

}
