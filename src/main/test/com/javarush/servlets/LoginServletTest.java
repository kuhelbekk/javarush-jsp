package com.javarush.servlets;

import com.javarush.quest.Quest;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServletTest {

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
    @Spy
    User user = new User("UserName");
    LoginServlet loginServlet;

    @BeforeEach
    void setUp() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getAttribute(eq("users"))) .thenReturn(users);
        when(context.getAttribute(eq("quest"))) .thenReturn(quest);
        when(context.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);
        loginServlet = new LoginServlet();
        loginServlet.init(servletConfig);
    }

    @Test
    void doGetNewUser() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("fname")).thenReturn("UserName");
        when(users.getOrCreateUser("UserName")).thenReturn(user);
        loginServlet.doGet(request, response);
        verify(user).incGameCount();
        verify(user).setCurrentQuest(0);
        verify(session).setAttribute("userData", user);
        verify(session).setAttribute("question", quest.getQuestion(0));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doGetResetGame() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("fname")).thenReturn(null);
        when(session.getAttribute("userData")).thenReturn(user);
        loginServlet.doGet(request, response);
        verify(user).incGameCount();
        verify(user).setCurrentQuest(0);
        verify(session).setAttribute("userData", user);
        verify(session).setAttribute("question", quest.getQuestion(0));
        verify(requestDispatcher).forward(request, response);
    }


    @Test
    void doGetNoName() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("fname")).thenReturn(null);
        when(session.getAttribute("userData")).thenReturn(null);

        loginServlet.doGet(request, response);
        verify(user,never()).incGameCount();
        verify(user,never()).setCurrentQuest(0);
        verify(session,never()).setAttribute("userData", user);
        verify(session,never()).setAttribute("question", quest.getQuestion(0));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doGetSpaceName() throws ServletException, IOException {
        when(request.getParameter("fname")).thenReturn(" ");
        loginServlet.doGet(request, response);
        verify(user, never()).incGameCount();
        verify(user, never()).setCurrentQuest(0);
        verify(session, never()).setAttribute("userData", user);
        verify(session, never()).setAttribute("question", quest.getQuestion(0));
        verify(requestDispatcher).forward(request, response);
    }
    @Test
    void doGetBlankName2() throws ServletException, IOException {
        when(request.getParameter("fname")).thenReturn("");
        loginServlet.doGet(request, response);
        verify(user, never()).incGameCount();
        verify(user, never()).setCurrentQuest(0);
        verify(session, never()).setAttribute("userData", user);
        verify(session, never()).setAttribute("question", quest.getQuestion(0));
        verify(requestDispatcher).forward(request, response);
    }
}