package ru.javarush.quest.stepanov.questdelta.filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.javarush.quest.stepanov.questdelta.util.RepositoryLoader;

@WebListener
public class DataLoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RepositoryLoader.load();
    }
}

