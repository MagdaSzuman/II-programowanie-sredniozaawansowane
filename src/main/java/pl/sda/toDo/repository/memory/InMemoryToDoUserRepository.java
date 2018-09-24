package pl.sda.toDo.repository.memory;

import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.repository.ToDoUserRepository;

public class InMemoryToDoUserRepository implements ToDoUserRepository {
    @Override
    public boolean save(ToDoUser user) {
        return false;
    }

    @Override
    public ToDoUser findByName(String name) {
        return null;
    }

    @Override
    public boolean exists(String name) {
        return false;
    }
}
