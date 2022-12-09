package com.javarush.quest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.servlets.LoginServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionLoader {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    public static List<Question> getQuestions(String filenName) {
        try {
            ObjectMapper om = new ObjectMapper();
            URL resource = QuestionLoader.class.getClassLoader().getResource(filenName);
            Question[] questions = om.readValue(resource, Question[].class);
            return Arrays.stream(questions).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error load quest. " + e.toString());
            throw new RuntimeException("Error load quest.", e);
        }
    }

}
