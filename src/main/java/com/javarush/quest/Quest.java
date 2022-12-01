package com.javarush.quest;

import com.javarush.servlets.AppContextListener;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Quest {

    private static final Logger LOGGER = LogManager.getLogger(Quest.class);
    private List<Question> questions;

    public Quest(String QuestfileName) {
        LOGGER.debug( "####################################" );
        for (int i = 0; i < 10; i++) {
            LOGGER.fatal( "!!!!!!!!!!!!!!!!!!load Quest from" );
        }
        LOGGER.debug( "####################################" );
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
        for (Answer answer : getQuestion(oldIndex).answers) {
            if (answer.getId()==index) return true;
        }
        return false;
    }
}