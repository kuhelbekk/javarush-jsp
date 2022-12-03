package com.javarush.users;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    private final String name;
    @Getter
    private int gameCount = 0;
    @Getter
    private int winGameCount = 0;
    @Getter
    private int lostGameCount = 0;

    @Getter
    @Setter
    private int currentQuest = 0;


    public User(String name) {
        if (name == null) {
            throw new IllegalArgumentException("User name is null");
        }
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("User name is empty");
        }
        this.name = name;
    }

    public void incGameCount() {
        gameCount++;
    }

    public void incWinGameCount() {
        winGameCount++;
    }

    public void incLostGameCount() {
        lostGameCount++;
    }
}
