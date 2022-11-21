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


@WebServlet(name = "ActionServlet", value = "/action")
public class ActionServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(StartServlet.class);
    Quest quest;
    Users users;

    @Override
    public void init(ServletConfig config ) throws ServletException {
        super.init(config);
        LOGGER.debug("init ActionServlet");
        var serverletContext =  config.getServletContext();
        users  = (Users) serverletContext.getAttribute("users");
        quest  = (Quest) serverletContext.getAttribute("quest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String answerId = request.getParameter("clickId");
        boolean isNumeric = answerId.chars().allMatch(Character::isDigit);
        int index = isNumeric ? Integer.parseInt(answerId) : 0;
        User user = (User) currentSession.getAttribute("userData");
        user.setCurrentQuest(index);
        currentSession.setAttribute("question", quest.getQuestion(index));
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String clickId = request.getParameter("clickId");
        boolean isNumeric = clickId.chars().allMatch(Character::isDigit);
        int index = isNumeric ? Integer.parseInt(clickId) : 0;
        User user = (User) request.getSession().getAttribute("userData");
        user.setCurrentQuest(index);
        request.getSession().setAttribute("question", quest.getQuestion(index));
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }


}
