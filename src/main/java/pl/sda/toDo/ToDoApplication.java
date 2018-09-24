package pl.sda.toDo;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.repository.ToDoRepository;
import pl.sda.toDo.repository.ToDoUserRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoUserRepository;
import pl.sda.toDo.service.ToDoService;
import pl.sda.toDo.views.ToDoConsoleView;

import java.util.Scanner;

@AllArgsConstructor
public class ToDoApplication {
    private ToDoService toDoService;
    private ToDoConsoleView toDoConsoleView;
    private ToDoUser currentUser;

    public static void main(String[] args) {
        ToDoRepository toDoRepository = new InMemoryToDoRepository();
        ToDoUserRepository toDoUserRepository = new InMemoryToDoUserRepository();

        ToDoService toDoService = new ToDoService(toDoRepository, toDoUserRepository);

        Scanner scanner = new Scanner(System.in);
        ToDoConsoleView toDoConsoleView = new ToDoConsoleView(scanner);

        ToDoApplication toDoApplication = new ToDoApplication(toDoService, toDoConsoleView, null);
        toDoApplication.start();
    }

    public void start() {
        do {
            Integer menuOption = toDoConsoleView.menu();
            switch (menuOption) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    addNewToDo();
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (true);
    }

    private void addNewToDo() {
        if (currentUser == null) {
            String name = toDoConsoleView.logInName();
            String password = toDoConsoleView.logInPassword();
            this.currentUser = toDoService.login(name, password);
        }
        String toDoName = toDoConsoleView.createNewToDoName();
        String toDoDescription = toDoConsoleView.createNewToDoDescription();

        ToDo toDo = new ToDo(toDoName, this.currentUser);
        toDo.setDescription(toDoDescription);
        toDoService.save(toDo);
    }
}