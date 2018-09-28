package pl.sda.toDo;

import lombok.AllArgsConstructor;
import pl.sda.toDo.model.Command;
import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoStatus;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.model.exception.InvalidPasswordException;
import pl.sda.toDo.model.exception.ToDoUserDoesNotExistsException;
import pl.sda.toDo.repository.ToDoRepository;
import pl.sda.toDo.repository.ToDoUserRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoRepository;
import pl.sda.toDo.repository.memory.InMemoryToDoUserRepository;
import pl.sda.toDo.service.ToDoService;
import pl.sda.toDo.views.ToDoConsoleView;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;
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
                    showToDoList();
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

        if (currentUser != null) {
            String toDoName = toDoConsoleView.createNewToDoName();
            String toDoDescription = toDoConsoleView.createNewToDoDescription();

            ToDo toDo = new ToDo(toDoName, this.currentUser);
            toDo.setDescription(toDoDescription);
            toDoService.save(toDo);
        }
    }

    private void showToDoList() {
        Integer option = toDoConsoleView.showToDoListWithOptions(toDoService.findAllToDos());
//        System.out.println("Wybrano opcję " + option);
        Command command = new Command(option);

        switch (option) {
            case 1:
                String possibleId = toDoConsoleView.getPossibleId();
                Integer toDoId = extractToDoId(possibleId);
                command.addArgument("toDoId", toDoId);
                showToDo(command);
                break;
            case 2:
                String possibleIdToRemove = toDoConsoleView.getPossibleId();
                Integer toDoIdToRemove = extractToDoId(possibleIdToRemove);
                command.addArgument("toDoId", toDoIdToRemove);
                deleteToDo(command);
                break;
            case 3:
                String possibleIdToAssign = toDoConsoleView.getPossibleId();
                Integer toDoIdToAssign = extractToDoId(possibleIdToAssign);
                command.addArgument("toDoId", toDoIdToAssign);
                command.addArgument("user", currentUser);
                assign(command);
                break;
            case 4:
                addChangeStatusArguments(command);
                changeStatus(command);
                break;
        }
    }


    private void addChangeStatusArguments(Command command) {
        String restOfCommand = toDoConsoleView.getPossibleId();
        Scanner scanner = new Scanner(restOfCommand);
        if (scanner.hasNextInt()) {
            command.addArgument("toDoId", scanner.nextInt());
        }else {
            command.addArgument("toDoId", toDoConsoleView.getToDoId());
        }

        if (scanner.hasNext()) {
            String status = scanner.next();
            command.addArgument("status", ToDoStatus.valueOf(status));
        }else {
            command.addArgument("status", toDoConsoleView.getStatus());
        }
    }

    private void changeStatus(Command command) {
        Integer toDoId = (Integer) command.getArgument("toDoId")-1;
        ToDoStatus status = (ToDoStatus) command.getArgument("status");
        Optional<ToDo> toDo = toDoService.findToDoById(toDoId);
        if (toDo.isPresent()) {
            ToDo toDoToChangeStatus = toDo.get();
            toDoToChangeStatus.setToDoStatus(status);
        }
        toDoConsoleView.displayChangeStatus(toDo);
    }

    // Stara metoda przed command
//    private void assign(String possibleId, ToDoUser currentUser) {
//        Integer toDoId = extractToDoId(possibleId);
//        Optional<ToDo> toDo = toDoService.findToDoById(toDoId);
//        if (toDo.isPresent()) {
//            ToDo toDoToChangeAssigment = toDo.get();
//            toDoToChangeAssigment.setOwner(currentUser);
//        }
//        toDoConsoleView.displayAssigment(toDo, currentUser);
//    }

    private void assign(Command command) {
        Integer toDoId = (Integer) command.getArgument("toDoId");
        ToDoUser user = (ToDoUser) command.getArgument("user");

        Optional<ToDo> toDo = toDoService.findToDoById(toDoId);
        if (toDo.isPresent()) {
            ToDo toDoToChangeAssigment = toDo.get();
            toDoToChangeAssigment.setOwner(user);
        }
        toDoConsoleView.displayAssigment(toDo, user);
    }

    private Integer extractToDoId(String possibleId) {
        Integer toDoId;
        if (possibleId.length() == 0) {
            toDoId = toDoConsoleView.getToDoId() - 1;
        } else {
            toDoId = Integer.valueOf(possibleId) - 1;
        }
        return toDoId;
    }

    // stara metoda deletoToDo - w nowej wyciąganie id jest na innym poziomie - command
//    private void deleteToDo(String possibleId) {
//        // NAPISANE OD NOWA
////        Integer toDoToDeleteId = toDoConsoleView.getToDoIdToDelete()-1;
////        toDoService.removeToDo(toDoToDeleteId);
//        Integer toDoId = extractToDoId(possibleId);
//        Optional<ToDo> removedToDo = toDoService.removeToDo(toDoId);
//        toDoConsoleView.displayToDoRemove(removedToDo);
//    }

    private void deleteToDo(Command command) {
        Integer toDoId = (Integer) command.getArgument("toDoId");
        Optional<ToDo> removedToDo = toDoService.removeToDo(toDoId);
        toDoConsoleView.displayToDoRemove(removedToDo);
    }
    // stara metoda showToDo - w nowej wyciąganie id jest na innym poziomie
//    private void showToDo(String possibleId) {
//        Integer toDoId = extractToDoId(possibleId);
//        Optional<ToDo> toDo = toDoService.findToDoById(toDoId);
//        toDoConsoleView.showToDoWithDetails(toDo);
//    }

    private void showToDo(Command command) {
        Integer toDoId = (Integer) command.getArgument("toDoId");
        Optional<ToDo> toDo = toDoService.findToDoById(toDoId);
        toDoConsoleView.showToDoWithDetails(toDo);
    }
}