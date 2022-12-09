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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogOutServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    ServletConfig servletConfig;
    LogOutServlet logOutServlet;

    @BeforeEach
    void setUp() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(eq("/WEB-INF/index.jsp"))).thenReturn(requestDispatcher);
        logOutServlet = new LogOutServlet();
        logOutServlet.init(servletConfig);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        logOutServlet.doGet(request, response);
        verify(session).invalidate();
        verify(requestDispatcher).forward(request, response);
    }



}