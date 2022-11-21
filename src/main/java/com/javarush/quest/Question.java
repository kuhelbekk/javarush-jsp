package com.javarush.quest;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Question{
    private int id;
    private String title;
    private String text;
    private String type;
    private String img;
    public ArrayList<Answer> answers;

    public String getAnswerText(int indexAnswer){
        if (answers.get(indexAnswer)!=null) {
            return answers.get(indexAnswer).getTitle();
        }
        return "";
    };
}

