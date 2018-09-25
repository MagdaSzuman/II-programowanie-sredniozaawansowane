package pl.sda.toDo.service;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.model.exception.InvalidPasswordException;
import pl.sda.toDo.model.exception.ToDoUserDoesNotExistsException;
import pl.sda.toDo.repository.ToDoRepository;
import pl.sda.toDo.repository.ToDoUserRepository;

import java.util.List;

@AllArgsConstructor
public class ToDoService {
    private ToDoRepository toDoRepository;
    private ToDoUserRepository toDoUserRepository;

    public void save(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public ToDoUser register(String name, String password) {
        if (toDoUserRepository.exists(name)) {
            return null;
        }
        ToDoUser user = new ToDoUser(name, password);
        toDoUserRepository.save(user);
        return user;
    }

    public ToDoUser login(String name, String password) {
        if (!toDoUserRepository.exists(name)){
            throw new ToDoUserDoesNotExistsException("User with name \"" + name + "\" does not exist");
        }
        ToDoUser user = toDoUserRepository.findByName(name);
        if (!user.getPassword().equals(password)){
            throw new InvalidPasswordException("Invalid password");
        }
        return user;
    }

    public List<ToDo> findAllToDos() {
        return toDoRepository.findAll();
    }
}
