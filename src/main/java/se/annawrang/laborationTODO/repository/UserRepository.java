package se.annawrang.laborationTODO.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.annawrang.laborationTODO.data.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u")
    List<User> findAll();

    User save(User user);

    @Override
    void delete(User user);
}
