package com.javarush.servlets;

import com.javarush.quest.Quest;
import com.javarush.user.Users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();

        Users users  = new Users();
        servletContext.setAttribute("users",users);
        Quest quest = new Quest("/question.json"); //  убрать в контекст
        servletContext.setAttribute("quest",quest);

    }
}
