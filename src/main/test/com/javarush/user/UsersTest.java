package com.javarush.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Set;

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
        try {
            Field field = Users.class.getDeclaredField("users");
            field.setAccessible(true);
            Set<User> u = (Set<User>) field.get(users);
            assertEquals( u.size(),1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        assertSame(user1, user2);

    }
}