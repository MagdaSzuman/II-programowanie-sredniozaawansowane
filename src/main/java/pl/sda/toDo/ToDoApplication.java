package pl.sda.toDo;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.model.exception.InvalidPasswordException;
import pl.sda.toDo.model.exception.ToDoUserDoesNotExistsException;
import pl.sda.toDo.repository.ToDoRepository;
import pl.sda.toDo.repository.ToDoUserRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoUserRepository;
import pl.sda.toDo.service.ToDoService;
import pl.sda.toDo.views.ToDoConsoleView;

import java.util.Arrays;
import java.util.Scanner;

@AllArgsConstructor
public class ToDoApplication {
    private ToDoService toDoService;
    private ToDoConsoleView toDoConsoleView;
    private ToDoUser currentUser;

    public static void main(String[] args) {
        ToDoRepository toDoRepository = new InMemoryToDoRepository();
        ToDoUserRepository toDoUserRepository = new InMemoryToDoUserRepository(
        );

        ToDoService toDoService = new ToDoService(toDoRepository, toDoUserRepository);

        Scanner scanner = new Scanner(System.in);
        ToDoConsoleView toDoConsoleView = new ToDoConsoleView(scanner);

        ToDoApplication toDoApplication = new ToDoApplication(toDoService, toDoConsoleView, null);
        toDoApplication.start();
    }

    public void start() {
        Boolean flag = true;
        do {
            Integer menuOption = toDoConsoleView.menu();
            switch (menuOption) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    addNewToDo();
                    break;
                case 4:
                    break;
                case 0:
                default:
                    toDoConsoleView.exit();
                    flag = false;
                    break;
            }
        } while (flag);
    }

    private void login() {
        this.currentUser = null;
        String name = toDoConsoleView.logInName();
        String password = toDoConsoleView.logInPassword();

        try {
            this.currentUser = toDoService.login(name, password);
        } catch (ToDoUserDoesNotExistsException | InvalidPasswordException e) {
            toDoConsoleView.displayError(e.getMessage());
        }

        if (this.currentUser != null) {
            toDoConsoleView.displaySuccess("User " + name + " is logged in");
        }
    }

    private void register() {
        String name = toDoConsoleView.registerName();
        String password = toDoConsoleView.registerPassword();
        ToDoUser user = toDoService.register(name, password);

        if (user == null) {
            toDoConsoleView.displayError("User cannot be registered. \n" + " User with that name " + name + " already exists");
        } else {
            toDoConsoleView.displaySuccess("User " + name + " is registered successfully");
        }
    }

    private void addNewToDo() {
        if (currentUser == null) {
            login();
        }
        String toDoName = toDoConsoleView.createNewToDoName();
        String toDoDescription = toDoConsoleView.createNewToDoDescription();

        ToDo toDo = new ToDo(toDoName, this.currentUser);
        toDo.setDescription(toDoDescription);
        toDoService.save(toDo);
    }
}