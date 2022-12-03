package com.javarush.servlets;

import com.javarush.quest.Quest;
import com.javarush.users.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("init AppContextListener");
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();
        Users users = new Users();
        servletContext.setAttribute("users", users);
        LOGGER.debug("users repository created");
        Quest quest = new Quest("/question.json"); //  убрать в контекст
        servletContext.setAttribute("quest", quest);
        LOGGER.debug("quest  loaded");
    }
}
