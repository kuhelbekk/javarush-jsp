package com.javarush.quest;

import java.util.List;

public class Quest {
    private final List<Question> questions;

    public Quest(List<Question> questions) {
        this.questions = questions;
    }

    public Question getQuestion(int index) {
        for (Question question : questions) {
            if (question.getId() == index)
                return question;
        }
        return questions.get(0);
    }

    public boolean isTransit(int oldIndex, int index) {
        for (Answer answer : getQuestion(oldIndex).getAnswers()) {
            if (answer.getId() == index) return true;
        }
        return false;
    }
}
