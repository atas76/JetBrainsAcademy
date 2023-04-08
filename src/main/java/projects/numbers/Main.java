package projects.numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number: ");
        int numberInput = scanner.nextInt();
        if (numberInput > 0) {
            new Number(numberInput).print();
        } else {
            System.out.println("This number is not natural!");
        }
    }
}
