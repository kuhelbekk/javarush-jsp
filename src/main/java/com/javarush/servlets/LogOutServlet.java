package com.javarush.servlets;


import com.javarush.quest.Quest;
import com.javarush.user.User;
import com.javarush.user.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LogOutServlet.class);
    Quest quest;
    Users users;

    @Override
    public void init(ServletConfig config ) throws ServletException {
        super.init(config);
        LOGGER.debug("init RestartServlet");
        var serverletContext =  config.getServletContext();
        users  = (Users) serverletContext.getAttribute("users");
        quest  = (Quest) serverletContext.getAttribute("quest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("LogOutServlet GET");
        HttpSession currentSession = request.getSession(true);
        currentSession.invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }
}
