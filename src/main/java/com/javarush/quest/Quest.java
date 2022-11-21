package com.javarush.quest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Quest {


    private List<Question> questions;

    public Quest(String QuestfileName) {
        questions = QuestionLoader.getQuestions(QuestfileName);
    }

    public Question getQuestion(int index){
        for (Question question : questions) {
            if (question.getId()==index)
                return question;
        }
        return questions.get(0);
    }

}