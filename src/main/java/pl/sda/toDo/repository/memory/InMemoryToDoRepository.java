package pl.sda.toDo.repository.memory;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.repository.ToDoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class InMemoryToDoRepository implements ToDoRepository {
    private List<ToDo> todos;

    public InMemoryToDoRepository() {
        this.todos = new ArrayList<>();
    }

    @Override
    public void save(ToDo toDo) {
        todos.add(toDo);
    }

    @Override
    public Optional<ToDo> findById(String id) {
        return todos.stream()
                .filter(e->e.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<ToDo> findById(Integer id) {
//        if (id >= 0 && id < todos.size()) {
//            return Optional.of(todos.get(id));
//        }
//        return Optional.empty();
        // ten sam zapis co na górze, tylko ładniejszy
        return (id >= 0 && id < todos.size())?
                Optional.of(todos.get(id)):
                Optional.empty();

    }

    @Override
    public List<ToDo> findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public void remove(int toDoId) {
        todos.remove(toDoId);
    }
}
