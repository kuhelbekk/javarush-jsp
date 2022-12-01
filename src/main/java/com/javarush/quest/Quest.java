package com.javarush.quest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Quest {

    private static final Logger LOGGER = LogManager.getLogger(Quest.class);
    private final List<Question> questions;
    public Quest(String QuestfileName) {
        LOGGER.info( "Load Quest from" +QuestfileName );
        questions = QuestionLoader.getQuestions(QuestfileName);
    }

    public Question getQuestion(int index){
        for (Question question : questions) {
            if (question.getId()==index)
                return question;
        }
        return questions.get(0);
    }

    public boolean isTransit(int oldIndex, int index) {
        for (Answer answer : getQuestion(oldIndex).getAnswers()) {
            if (answer.getId()==index) return true;
        }
        return false;
    }
}
