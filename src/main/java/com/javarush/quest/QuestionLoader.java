package com.javarush.quest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionLoader {
    static List<Question> getQuestions(String filenName) {
        try {
            ObjectMapper om = new ObjectMapper();
            URL resource = QuestionLoader.class.getClassLoader().getResource(filenName);
            Question[] questions = om.readValue( resource , Question[].class);
            return Arrays.stream(questions).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
