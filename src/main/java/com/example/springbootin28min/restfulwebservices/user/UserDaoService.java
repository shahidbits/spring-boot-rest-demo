package com.example.springbootin28min.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(userCount++, "Adam", new Date()));
        users.add(new User(userCount++, "Mary", new Date()));
        users.add(new User(userCount++, "Aaron", new Date()));
    }

    public List<User> get() {
        return users;
    }

    public User post(User user) {
        if (user.getId() == null) {
            user.setId(userCount++);
        }
        users.add(user);
        return user;
    }

    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
