package pl.sda.toDo.repository;

import pl.sda.toDo.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
    void save (ToDo toDo);
    Optional<ToDo> findById (String id);
    List<ToDo> findAll();
}

