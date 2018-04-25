package se.annawrang.laborationTODO.repository.jpa;

import org.springframework.stereotype.Component;
import se.annawrang.laborationTODO.data.User;
import se.annawrang.laborationTODO.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class JpaUserRepository{

    private AtomicLong ids = new AtomicLong(1000);
    private HashMap<Long, User> users = new HashMap<>();

    public HashMap<Long, User> getUsers() {
        return users;
    }

    public User getUser(Long id) {
        if (users.containsKey(id)){
            return users.get(id);
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        users.forEach((aLong, user) -> allUsers.add(user));
        return allUsers;
    }

    public User saveUser(User user) {
        return null;
    }
}
