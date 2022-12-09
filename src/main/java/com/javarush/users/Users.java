package com.javarush.users;

import java.util.HashSet;
import java.util.Set;

public class Users {
    private Set<User> users;

    public Users() {
        this.users = new HashSet<>();
    }

    public int getUserCount() {
        return users.size();
    }

    public User getOrCreateUser(String userName) {
        User user = null;
        if (userName == null) {
            throw new NullPointerException("UserName is null");
        }
        for (User userFor : users) {
            if (userFor.getName().equals(userName)) {
                user = userFor;
                break;
            }
        }
        if (user == null) {
            user = new User(userName);
            users.add(user);
        }
        return user;
    }
}
