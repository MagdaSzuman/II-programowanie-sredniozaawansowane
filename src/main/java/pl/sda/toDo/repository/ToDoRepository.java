package pl.sda.toDo.repository;

import pl.sda.toDo.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
    void save (ToDo toDo);
    Optional<ToDo> findById (String id);
    Optional<ToDo> findById (Integer id);
    List<ToDo> findAll();

    void remove(int toDoId);

}

