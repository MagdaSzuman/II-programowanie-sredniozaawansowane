package pl.sda.toDo.repository.memory;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.repository.ToDoUserRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class InMemoryToDoUserRepository implements ToDoUserRepository {

    private List<ToDoUser> users;

    public InMemoryToDoUserRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean save(ToDoUser user) {
        if (exists(user.getName())) {
            return false;
        }
        return users.add(user);
    }

    @Override
    public ToDoUser findByName(String name) {
        return users.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
//                .orElseGet(()->null);
                .orElse(null);
    }

    @Override
    public boolean exists(String name) {
        return users.stream()
                .anyMatch(e -> e.getName().equals(name));
    }
}
