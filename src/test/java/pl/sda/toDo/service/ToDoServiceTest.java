package pl.sda.toDo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sda.toDo.ToDoApplication;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.repository.ToDoRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    @Test
    public void findTodosByCreatorNameShouldReturnTodosForExistingCreatorName() {
        // mamy w liście todosy, które zostały utworzone przez użytkownika xyz i chcemy zwrócic te elementu
        // mock na to do repository i do zastabowania find all - niech zwraca 4 todosy i w pierwszym zadania ma zwracać 2

        //given
        ToDoRepository toDoRepository = Mockito.mock(ToDoRepository.class);
        Mockito.when(toDoRepository.findAll()).thenReturn(createSampleList());
        ToDoService toDoService = new ToDoService(toDoRepository, null);
        //when
        List<ToDo> todos = toDoService.findTodosByCreatorName("autor1");
        //then
        Assert.assertEquals(2, todos.size());
        todos.forEach(toDo -> {
            Assert.assertEquals("autor1", toDo.getCreator().getName());
        });
    }

    @Test
    public void findTodosByCreatorNameShouldReturnEmptyListForNonExistingCreator() {
        // zwraca pustą listę
//given
        ToDoRepository toDoRepository = Mockito.mock(ToDoRepository.class);
        Mockito.when(toDoRepository.findAll()).thenReturn(createSampleList());
        ToDoService toDoService = new ToDoService(toDoRepository, null);
        //when
        List<ToDo> todos = toDoService.findTodosByCreatorName("autorNieIstniejący");
        //then
        Assert.assertEquals(0, todos.size());
    }

    private List<ToDo> createSampleList() {
        ToDoUser toDoUser = new ToDoUser("autor1", "hasło");
        ToDoUser toDoUser2 = new ToDoUser("autor2", "hasło2");
        ToDoUser toDoUser3 = new ToDoUser("autor3", "hasło3");

        ToDo toDo1 = new ToDo("zadanie1", toDoUser);
        ToDo toDo2 = new ToDo("zadanie2", toDoUser);
        ToDo toDo3 = new ToDo("zadanie3", toDoUser2);
        ToDo toDo4 = new ToDo("zadanie4", toDoUser3);

        return Arrays.asList(toDo1, toDo2, toDo3, toDo4);
    }
}