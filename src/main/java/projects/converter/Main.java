package projects.converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String command;

        do {

            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            command = scanner.next();

            if ("/from".equals(command)) {

                StringBuilder result = new StringBuilder();

                System.out.println("Enter number in decimal system: ");
                int decimalNumber = scanner.nextInt();

                System.out.println("Enter target base: ");
                int targetBase = scanner.nextInt();

                int quotient = decimalNumber;

                while (quotient != 0) {
                    result.append(Character.forDigit(quotient % targetBase, targetBase));
                    quotient /= targetBase;
                }

                System.out.println("Conversion result: " + result.reverse());
                System.out.println();
            }
            if ("/to".equals(command)) {

            }
        } while (!"/exit".equals(command));
    }
}
