package pl.sda.toDo.repository;

import pl.sda.toDo.model.ToDoUser;

public interface ToDoUserRepository {
    boolean save (ToDoUser user);
    ToDoUser findByName (String name);
    boolean exists (String name);
}
