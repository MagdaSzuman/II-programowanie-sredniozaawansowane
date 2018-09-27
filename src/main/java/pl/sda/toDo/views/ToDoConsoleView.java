package pl.sda.toDo.views;

import pl.sda.toDo.model.ToDo;
import pl.sda.toDo.model.ToDoStatus;
import pl.sda.toDo.model.ToDoUser;
import pl.sda.toDo.repository.memory.InMemoryToDoRepository;
import pl.sda.toDo.service.ToDoService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ToDoConsoleView {
    private static Scanner scanner;

    public ToDoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu(){
        System.out.println();
        System.out.println("====== ToDo application =====");
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Dodaj zadanie");
        System.out.println("4. Wyświetl zadania");
        System.out.println("0. Koniec");
        System.out.println("=============================");

        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String logInName(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("Podaj nick");
        return scanner.nextLine();
    }
    public String logInPassword(){
        System.out.println();
        System.out.println("=============================");
        System.out.println("Podaj haslo");
        return scanner.nextLine();
    }

    public String registerName(){
        return logInName();
    }

    public String registerPassword(){
        return logInPassword();
    }

    public String createNewToDoName() {
        System.out.println();
        System.out.println("=============================");
        System.out.println("Podaj nazwe zadania");
        return scanner.nextLine();
    }

    public String createNewToDoDescription() {
        System.out.println();
        System.out.println("=============================");
        System.out.println("Podaj opis zadania");
        return scanner.nextLine();
    }

    public void displayError(String message){
        System.out.println();
        System.out.println("==========ERROR=========");
        System.out.println(message);
        System.out.println("==========ERROR=========");
        System.out.println();
    }

    public void displaySuccess(String message) {
        System.out.println();
        System.out.println("=========SUCCESS=========");
        System.out.println(message);
        System.out.println("=========SUCCESS=========");
        System.out.println();
    }

    public void exit() {
        System.out.println();
        System.out.println("=============================");
        System.out.println("Zapraszamy ponownie!");
        System.out.println("=============================");

    }

    public Integer showToDoListWithOptions(List<ToDo> allToDos) {
        System.out.println();
        System.out.println("=============================");
        System.out.println("Lista zadań");
        System.out.println("=============================");

        for (int i = 0; i <allToDos.size() ; i++) {
            ToDo toDo = allToDos.get(i);
            ToDoUser creator = toDo.getCreator();
            Optional<ToDoUser> owner = Optional.ofNullable(toDo.getOwner());
            ToDoStatus toDoStatus = toDo.getToDoStatus();
            System.out.println(
                    (i+1) +
                    ". | \"" + toDo.getName() +
                    "\" | \"" + creator.getName() +
                    "\" | \"" + owner.orElse(ToDoUser.unasigned()).getName() +
                    "\" | \"" + toDoStatus.toString().toUpperCase());
        }

        System.out.println();
        System.out.println("=============================");
        System.out.println("1. Wyswietl");
        System.out.println("2. Usuń");
        System.out.println("3. Przypisz");
        System.out.println("4. Zmień status");
        System.out.println("0. Wyjdź");
        System.out.println("=============================");

        Integer option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }

    public Integer getToDoId() {
        System.out.println("=============================");
        System.out.println("Podaj numer zadania");
        int toDoId = scanner.nextInt();
        scanner.nextLine();
        return toDoId;
    }

    public void showToDoWithDetails(Optional<ToDo> toDo) {
        String message = toDo.map(e ->{
            ToDoUser creator = e.getCreator();
            Optional<ToDoUser> owner = Optional.ofNullable(e.getOwner());
            return e.getName() +
                    " (" + e.getToDoStatus().toString() + ") (" + e.getCreationDate().toString() + ")\n" +
                    "Opis: " + e.getDescription()+"\n"+
                    "Twórca: " + creator.getName() +"\n"+
                    "Przypisane: " + owner.orElse(ToDoUser.unasigned()).getName();
        }).orElse("Wybrane zadanie nie istnieje");
        System.out.println(message);
    }
}
