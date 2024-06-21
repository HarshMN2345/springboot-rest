package com.harshmahajan.Rest.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static int usersCount=0;
    private static List<User> users = new ArrayList<User>();
    static{
        users.add(new User(++usersCount,"harsh", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Aakash", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount,"Akshay", LocalDate.now().minusYears(34)));
    }
    public List<User> getUsers() {
        return users;
    }
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public void DeleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
    public User save(User user) {
        user.setId(++usersCount);
       users.add(user);
       return user;
    }
}
