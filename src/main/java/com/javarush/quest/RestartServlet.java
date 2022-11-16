package com.javarush.quest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "RestartServlet", value = "/restart")
public class RestartServlet extends HttpServlet {

    public void init() {
        System.out.print("init RestartServlet");
        ServletConfig config = this.getServletConfig();
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String key = initParameterNames.nextElement();
            System.out.printf("%s: %s\n", key, config.getInitParameter(key));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        request.getSession().invalidate();
        String userName = request.getParameter("clickId");

        Quest quest = new Quest("/question.json"); //  убрать в контекст
        HttpSession currentSession = request.getSession(true);
        currentSession.setAttribute("quest",0);
        currentSession.setAttribute("question", quest.getQuestion(0));
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        String userName = request.getParameter("clickId");

        Quest quest = new Quest("/question.json"); //  убрать в контекст
        HttpSession currentSession = request.getSession(true);
        currentSession.setAttribute("quest",0);
        currentSession.setAttribute("question", quest.getQuestion(0));
        response.sendRedirect("/index.jsp");
    }
}
