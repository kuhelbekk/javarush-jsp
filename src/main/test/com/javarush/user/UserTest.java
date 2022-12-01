package com.javarush.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    public void initUser(){
        user = new User("name");
    }


    @Test
    void nullNameException(){
        assertThrows(IllegalArgumentException.class, ()->new User(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ","", "\t\t","\n"})
    void blankNameException(String name){
        assertThrows(IllegalArgumentException.class, ()->new User(name));
    }

    @Test
    void getName() {
        String name = "name";
        user = new User(name);
        assertEquals(name, user.getName());
    }

    @Test
    void getGameCount() {
        user.incGameCount();
        assertEquals(user.getGameCount(),1);
        user.incGameCount();
        assertEquals(user.getGameCount(),2);
    }

    @Test
    void getWinGameCount() {
        user.incWinGameCount();;
        assertEquals(user.getWinGameCount(),1);
        user.incWinGameCount();
        assertEquals(user.getWinGameCount(),2);
    }

    @Test
    void getLostGameCount() {
        user.incLostGameCount();
        assertEquals(user.getLostGameCount(),1);
        user.incLostGameCount();
        assertEquals(user.getLostGameCount(),2);
    }

    @Test
    void getSetCurrentQuest() {
        assertEquals(user.getCurrentQuest(),0);
        user.setCurrentQuest(1);
        assertEquals(user.getCurrentQuest(),1);
    }


}
