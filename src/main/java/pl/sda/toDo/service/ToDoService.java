package pl.sda.toDo.service;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.model.exception.InvalidPasswordException;
import pl.sda.toDo.model.exception.ToDoUserAlreadyExistsException;
import pl.sda.toDo.repository.ToDoRepository;
import pl.sda.toDo.repository.ToDoUserRepository;

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
            throw new ToDoUserAlreadyExistsException("User with name " + name + " already exists");
        }
        ToDoUser user = toDoUserRepository.findByName(name);
        if (!user.getPassword().equals(password)){
            throw new InvalidPasswordException("Invalid password");
        }
        return user;
    }

}
