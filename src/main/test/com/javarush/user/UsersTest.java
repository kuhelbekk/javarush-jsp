package com.javarush.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UsersTest {
    Users users;
    @BeforeEach
    void setUp() {
        users = new Users();
    }

    @Test
    void getOrCreateUser() {
        String newUserName = "newUser";
        User user1 = users.getOrCreateUser(newUserName);
        User user2 = users.getOrCreateUser(newUserName);
        assertSame(user1, user2);

    }
}