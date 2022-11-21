package com.javarush.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.javarush.quest.Quest;
import com.javarush.user.User;
import com.javarush.user.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "RestartServlet", value = "/restart")
public class StartServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(StartServlet.class);

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
        LOGGER.debug("RestartServlet GET");
        String userName = request.getParameter("fname");
        User userData  = users.getOrCreateUser(userName);
        HttpSession currentSession = request.getSession(true);
        currentSession.setAttribute("userData", userData);
        currentSession.setAttribute("question", quest.getQuestion(userData.getCurrentQuest()));
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
    }


}
