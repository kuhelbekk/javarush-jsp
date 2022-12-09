package com.javarush.servlets;

import com.javarush.quest.Quest;
import com.javarush.quest.Question;
import com.javarush.users.User;
import com.javarush.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ActionServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ServletConfig servletConfig;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    Users users;
    @Mock
    Quest quest;
    @Mock
    Question question;
    @Spy
    User user = new User("UserName");
    ActionServlet actionServlet;

    @BeforeEach
    void setUp() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getAttribute(eq("users"))) .thenReturn(users);
        when(context.getAttribute(eq("quest"))) .thenReturn(quest);

        actionServlet = new ActionServlet();
        actionServlet.init(servletConfig);
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(context.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("clickId")).thenReturn("1");
        when(session.getAttribute("userData")).thenReturn(user);
        when(quest.isTransit(0,1)).thenReturn(true);
        when(quest.getQuestion(1)).thenReturn(question);
        when(question.getType()).thenReturn("");

        actionServlet.doPost(request, response);
        verify(user).setCurrentQuest(1);
        verify(user,never()).incWinGameCount();
        verify(user,never()).incLostGameCount();
        verify(quest).isTransit(0,1);
        verify(session).setAttribute("question", question);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPostHack() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("clickId")).thenReturn("1");
        when(session.getAttribute("userData")).thenReturn(user);
        when(quest.isTransit(0,1)).thenReturn(false);
        assertThrows(RuntimeException.class, ()->actionServlet.doPost(request, response));
    }

    @Test
    void doPostWin() throws ServletException, IOException {
        when(context.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("clickId")).thenReturn("1");
        when(session.getAttribute("userData")).thenReturn(user);
        when(quest.isTransit(0,1)).thenReturn(true);
        when(quest.getQuestion(1)).thenReturn(question);
        when(question.getType()).thenReturn("win");

        actionServlet.doPost(request, response);
        verify(user).setCurrentQuest(1);
        verify(user, times(1)).incWinGameCount();
        verify(user,never()).incLostGameCount();
        verify(quest).isTransit(0,1);
        verify(session).setAttribute("question", question);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPostLoss() throws ServletException, IOException {
        when(context.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("clickId")).thenReturn("1");
        when(session.getAttribute("userData")).thenReturn(user);
        when(quest.isTransit(0,1)).thenReturn(true);
        when(quest.getQuestion(1)).thenReturn(question);
        when(question.getType()).thenReturn("loss");

        actionServlet.doPost(request, response);
        verify(user).setCurrentQuest(1);
        verify(user, never()).incWinGameCount();
        verify(user,times(1)).incLostGameCount();
        verify(quest).isTransit(0,1);
        verify(session).setAttribute("question", question);
        verify(requestDispatcher).forward(request, response);
    }
}




