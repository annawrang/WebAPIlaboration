package se.annawrang.laborationTODO.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.annawrang.laborationTODO.data.Todo;
import se.annawrang.laborationTODO.data.User;
import se.annawrang.laborationTODO.repository.TodoRepository;
import se.annawrang.laborationTODO.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final TodoRepository repositoryTodo;

    public UserService(UserRepository repository, TodoRepository repositoryTodo){
        this.repository = repository;
        this.repositoryTodo = repositoryTodo;
    }

    public User getUser(Long id){
        Optional<User> u = repository.findById(id);
        return u.orElse(null);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public void deleteUser(User user){
        repository.delete(user);
    }

    public Todo getTodo(Long id){
        Optional<Todo> todo = repositoryTodo.findById(id);
        return todo.orElse(null);
    }

    public Todo saveTodo(Todo todo){
        return repositoryTodo.save(todo);
    }
}
