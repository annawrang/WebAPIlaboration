package se.annawrang.laborationTODO.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.annawrang.laborationTODO.data.Todo;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    Optional<Todo> findById(Long id);

    @Query("SELECT t FROM Todo t")
    List<Todo> getAll();

    Todo save(Todo todo);

    @Override
    void delete(Todo todo);
}
