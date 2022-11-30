package com.javarush.user;

import java.util.HashSet;
import java.util.Set;

public class Users {
    private Set<User> users;

    public Users()
    {
        this.users = new HashSet<>();
    }
    public User getOrCreateUser(String userName){
        User user = null;
        for (User userFor : users) {
            if (userFor.getName().equals(userName)) {
                user =  userFor;
                break;
            }
        }
        if (user == null){
            user =new User(userName);
            users.add(user);
        }
        return user;
    }
}
