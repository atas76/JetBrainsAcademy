package projects.numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
        System.out.println("Enter a request: ");

        long numberInput = scanner.nextLong();
        while (numberInput != 0) {
            if (numberInput > 0) {
                new Number(numberInput).print();
            } else {
                System.out.println("The first parameter should be a natural number or zero.");
            }
            System.out.println();
            System.out.println("Enter a request: ");
            numberInput = scanner.nextLong();
        }

        System.out.println();
        System.out.println("Goodbye!");
    }
}
