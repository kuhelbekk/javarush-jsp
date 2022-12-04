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
import com.javarush.users.User;
import com.javarush.users.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    Quest quest;
    Users users;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        LOGGER.debug("init RestartServlet");
        var serverletContext = config.getServletContext();
        users = (Users) serverletContext.getAttribute("users");
        quest = (Quest) serverletContext.getAttribute("quest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("login GET");
        String userName = request.getParameter("fname");
        User userData;
        if (userName == null) {
            userData = (User) request.getSession().getAttribute("userData");
            if (userData == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                return;
            }
        } else {
            if (userName.isEmpty() || userName.isBlank()) {
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                return;
            }
            userData = users.getOrCreateUser(userName);
        }
        HttpSession currentSession = request.getSession();
        userData.incGameCount();
        userData.setCurrentQuest(0);
        currentSession.setAttribute("userData", userData);
        currentSession.setAttribute("question", quest.getQuestion(userData.getCurrentQuest()));
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
