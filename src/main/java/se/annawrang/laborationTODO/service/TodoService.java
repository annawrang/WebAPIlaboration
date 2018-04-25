package se.annawrang.laborationTODO.service;

import org.springframework.stereotype.Service;
import se.annawrang.laborationTODO.data.Todo;
import se.annawrang.laborationTODO.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository repository;

    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    public Todo getTodo(Long id){
        if(repository.findById(id).isPresent()){
            Optional<Todo> todo = repository.findById(id);
            return todo.get();
        }
        return null;
    }

    public Todo saveTodo(Todo todo){
        return repository.save(todo);
    }

    public List<Todo> getAllTodos(){
        return repository.getAll();
    }

    public void deleteTodo(Todo todo){
        repository.delete(todo);
    }
}
