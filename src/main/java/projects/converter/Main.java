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

                int result = 0;

                System.out.println("Enter source number: ");
                String sourceNumber = scanner.next();

                System.out.println("Enter source base:");
                int sourceBase = scanner.nextInt();

                if (sourceBase != 16) {
                    for (int i = 0; i < sourceNumber.length(); i++) {
                        result += (sourceNumber.charAt(i) - '0') * Math.pow(sourceBase, sourceNumber.length() - i - 1);
                    }
                } else {
                    for (int i = 0; i < sourceNumber.length(); i++) {
                        result += convertFromHex(sourceNumber.charAt(i)) *
                                Math.pow(sourceBase, sourceNumber.length() - i - 1);
                    }
                }

                System.out.println("Conversion to decimal result: " + result);
            }
        } while (!"/exit".equals(command));
    }

    private static int convertFromHex(char digit) {
        return (digit >= 65) ? 10 + Character.toLowerCase(digit) - 'a' : digit - '0';
    }
}
