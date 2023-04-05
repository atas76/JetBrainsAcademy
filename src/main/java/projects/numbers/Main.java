package projects.numbers;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number: ");
        String numberInput = scanner.next();
        int number = Integer.parseInt(numberInput);
        if (number > 0) {
            String parity = number % 2 == 0 ? "Even" : "Odd";
            boolean divisibleBySeven = (number % 7 == 0);
            boolean lastDigitSeven = numberInput.endsWith("7");
            boolean isBuzz = divisibleBySeven || lastDigitSeven;

            System.out.println("This number is " + parity + ".");
            System.out.println("It is " + (isBuzz ? "" : "not ") + "a Buzz number.");
            System.out.println("Explanation:");
            if (!isBuzz) {
                System.out.println(numberInput + " is neither divisible by 7 nor does it end with 7.");
            } else {
                if (divisibleBySeven && !lastDigitSeven) {
                    System.out.println(numberInput + " is divisible by 7.");
                } else if (!divisibleBySeven) {
                    System.out.println(numberInput + " ends with 7.");
                } else {
                    System.out.println(numberInput + " is divisible by 7 and ends with 7.");
                }
            }
        } else {
            System.out.println("This number is not natural!");
        }
    }
}
