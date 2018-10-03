package pl.sda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileApplication {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\M\\Desktop\\IntelliJ - projekty\\programowanie-sredniozaawansowane\\src\\main\\resources\\test");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
