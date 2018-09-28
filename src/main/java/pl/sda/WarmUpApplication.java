package pl.sda;

import java.util.Scanner;

public class WarmUpApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Dodawanie");
        System.out.println("2. Odejmowanie");
        System.out.println("3. Dzielenie");
        System.out.println("4. Monożenie");
        System.out.println("5. Suma wielu");

        int operation = scanner.nextInt();
        if (operation > 0 && operation < 5) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            double result = perfomOperation(operation, a, b);
            System.out.println("Result: " + result);
        } else {
            int sum = 0;
            while (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            }
            System.out.println("Suma wielu:: " + sum);
        }

    }

    private static double perfomOperation(int operation, int a, int b) {
        double result;
        switch (operation) {
            case 1:
                result = a + b;
                break;
            case 2:
                result = a - b;
                break;
            case 3:
                result = a / (double) b;
                break;
            case 4:
                result = a * b;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
// DZIEŁO SZUMANA
//    Scanner scanner = new Scanner(System.in);
//    public static void main(String[] args) {
//        Integer menu = menu();
//        switch (menu){
//            case 1:
//                sum();
//                break;
//
//        }
//
//
//    }
//
//    public void menu() {
//        Scanner scanner = new Scanner(System.in)
//        System.out.println("1. Dodawanie");
//        System.out.println("2. Odejmowanie");
//        System.out.println("3. Dzielenie");
//        System.out.println("4. Monożenie");
//        System.out.println("Choose numbers and set action");
//        scanner.nextLine();
//    }
//
//    public static void sum(){
//        int sum = 0;
//        while (scanner.hasNextInt()){
//            sum +=  scanner.nextInt();
//        }
//        System.out.println(sum);
//    }
//}
