package pl.sda.menu;

import pl.sda.toDo.model.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuViews {
    private Scanner scanner;

    public MenuViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command menu() {
        System.out.println("1. Graj");
        System.out.println("2. Wyniki");
        System.out.println("3. Koniec");

        int option = scanner.nextInt();
        return new Command(option);
    }

    public Command startGameMenu() {
        System.out.println("1. Podaj imię");
        System.out.println("2. Podaj wiek");
        System.out.println("3. Podaj atrybuty");
        int option = scanner.nextInt();
        Command command = new Command(option);
        switch (option) {
            case 1:
                String name = scanner.nextLine();
                command.addArgument("name:", name);
                break;
            case 2:
                int age = scanner.nextInt();
                command.addArgument("age", age);
                break;
            case 3:
                List<String> attributes = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String attribute = scanner.nextLine();
                    if (attribute.equals("end")){
                        break;
                    }
                    attributes.add(attribute);
                }
                command.addArgument("attributes", attributes);
                break;
        }
        return command;
    }
}
