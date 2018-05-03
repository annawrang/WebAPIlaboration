package se.annawrang.laborationTODO.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.annawrang.laborationTODO.data.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    @Query("SELECT u FROM User u left join fetch u.todos t where u.id= :idIn")
    Optional<User> findById(Long idIn);

    @Query("SELECT u FROM User u left join fetch u.todos t")
    List<User> findAll();
}
