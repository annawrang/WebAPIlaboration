package se.annawrang.laborationTODO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.annawrang.laborationTODO.data.User;
import se.annawrang.laborationTODO.repository.UserRepository;
import se.annawrang.laborationTODO.repository.jpa.JpaUserRepository;
import se.annawrang.laborationTODO.resourse.UserResource;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }


    public User getUser(Long id){
        Optional<User> u = repository.findById(id);
        if(u.isPresent()){
            return u.get();}
        return null;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }
}
