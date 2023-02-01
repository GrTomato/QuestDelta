package ru.javarush.quest.stepanov.questdelta.filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.service.InitService;

@WebListener
public class DataLoadListener implements ServletContextListener {

    private final InitService initService;

    public DataLoadListener() {
        this.initService = Winter.getBean(InitService.class);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initService.load();
    }
}

