package com.javarush.quest;

import com.javarush.servlets.ActionServlet;
import com.javarush.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class QuestTest {
    Quest quest;
    @Mock
    Question question0;
    @Mock
    Question question1;
    @Mock
    Answer answer;
    @Spy
    List<Question> questions;
    @BeforeEach
    void setUp() throws ServletException {
        questions = new ArrayList<Question>();
        questions.add(question0);
        questions.add(question1);

        when(question0.getId()).thenReturn(0);
        quest = new Quest(questions);
    }
    @Test
    void getQuestion() {

        when(question1.getId()).thenReturn(1);
        assertEquals( quest.getQuestion(0), question0) ;
        assertEquals( quest.getQuestion(1), question1) ;
    }

    @Test
    void isTransit() {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        when(answer.getId()).thenReturn(1);
        when(question0.getAnswers()).thenReturn(answers);
        assertTrue(quest.isTransit(0,1));
        verify(answer).getId();

    }
}
