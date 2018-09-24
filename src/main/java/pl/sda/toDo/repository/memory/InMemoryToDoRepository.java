package pl.sda.toDo.repository.memory;

import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.repository.ToDoRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryToDoRepository implements ToDoRepository {

    @Override
    public void save(ToDo toDo) {

    }

    @Override
    public Optional<ToDo> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<ToDo> findAll() {
        return null;
    }
}
