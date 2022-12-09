package com.javarush.servlets;

import com.javarush.quest.Quest;
import com.javarush.users.User;
import com.javarush.users.Users;
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
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private Quest quest;
    private Users users;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        LOGGER.debug("init ActionServlet");
        var serverletContext = config.getServletContext();
        users = (Users) serverletContext.getAttribute("users");
        quest = (Quest) serverletContext.getAttribute("quest");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clickId = request.getParameter("clickId");
        boolean isNumeric = clickId.chars().allMatch(Character::isDigit);
        int index = isNumeric ? Integer.parseInt(clickId) : 0;
        User user = (User) request.getSession().getAttribute("userData");
        int oldIndex = user.getCurrentQuest();
        if (!quest.isTransit(oldIndex, index) || oldIndex == index) {
            throw new RuntimeException("You broke this site! Why? Who will fix it now?");
        }
        user.setCurrentQuest(index);
        if (quest.getQuestion(index).getType().equals("win")) user.incWinGameCount();
        if (quest.getQuestion(index).getType().equals("loss")) user.incLostGameCount();

        request.getSession().setAttribute("usersCount", users.getUserCount());
        request.getSession().setAttribute("question", quest.getQuestion(index));
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }


}
