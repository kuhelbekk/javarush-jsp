package com.javarush.servlets;

import com.javarush.quest.Quest;
import com.javarush.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppContextListenerTest {
    @Mock
    ServletContextEvent servletContextEvent;
    @Mock
    ServletContext context;
    AppContextListener contextListener;

    @BeforeEach
    void setup() {
        when(servletContextEvent.getServletContext()).thenReturn(context);
        contextListener = new AppContextListener();
    }

    @Test
    void contextInitialized() {
        contextListener.contextInitialized(servletContextEvent);
        verify(context).setAttribute("users",any());
        verify(context).setAttribute("quest",any());
    }


}
