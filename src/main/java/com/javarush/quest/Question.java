package com.javarush.quest;

import lombok.Getter;


import java.util.List;

@Getter
public class Question {
    private int id;
    private String title;
    private String text;
    private String type;
    private String img;
    private List<Answer> answers;

}

