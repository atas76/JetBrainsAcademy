package projects.bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static String secretCode;
    private static Random rnd = new Random();

    private static char [] symbols;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String secretCodeLength = scanner.nextLine();

        int secretCodeSymbolsNum = 0;
        try {
            secretCodeSymbolsNum = Integer.parseInt(secretCodeLength);
            if (secretCodeSymbolsNum <= 0) {
                System.out.println("Error: secret code length must be a number above zero");
                System.exit(4);
            }
        } catch (NumberFormatException nfe) {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", secretCodeLength);
            System.exit(2);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int numberOfSymbols = scanner.nextInt();

        if (numberOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(3);
        }

        if (numberOfSymbols < secretCodeSymbolsNum) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n", secretCodeSymbolsNum, numberOfSymbols);
            System.exit(1);
        }

        symbols = new char[numberOfSymbols];

        for (int i = 0; i < Math.min(numberOfSymbols, 10); i++) {
            symbols[i] = (char) (i + '0');
        }

        if (numberOfSymbols > 10) {
            for (int i = 10; i < numberOfSymbols; i++) {
                symbols[i] = (char) (i + 'a' - 10);
            }
        }

        // System.out.println(Arrays.toString(symbols));

        System.out.println("The secret is prepared: " + getSecretCodePresentation(numberOfSymbols, secretCodeSymbolsNum));

        secretCode = generateSecretCode(secretCodeSymbolsNum);
        System.out.println("The random secret number is " + secretCode);

        int turn = 1;
        boolean guessed = false;

        System.out.println("Okay, let's start a game!");

        while (!guessed) {
            System.out.println("Turn " + turn++ + ":");
            guessed = playTurn(scanner.next());
            if (guessed) {
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }
    }

    private static String getSecretCodePresentation(int numberOfSymbols, int codeDigitsNum) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codeDigitsNum; i++) {
            sb.append('*');
        }

        sb.append(" (").append(symbols[0]).append('-').append(symbols[Math.min(9, numberOfSymbols - 1)]);
        if (numberOfSymbols > 10) {
            sb.append(", ").append(symbols[10]).append('-').append(symbols[symbols.length - 1]).append(").");
        }

        return sb.toString();
    }

    private static String generateSecretCode(int numberOfSymbols) {

        StringBuilder secretNumberBuilder = new StringBuilder(numberOfSymbols);

        while (secretNumberBuilder.length() < numberOfSymbols) {

            char currentSymbol = symbols[rnd.nextInt(symbols.length)];

            if (!secretNumberBuilder.toString().contains(String.valueOf(currentSymbol))) {
                secretNumberBuilder.append(currentSymbol);
            }
        }
        return secretNumberBuilder.toString();
    }

    private static boolean playTurn(String answer) {
        String grade = computeGrade(answer);
        System.out.println("Grade: " + grade + ".");
        return (secretCode.length() + " bull(s)").equals(grade);
    }

    private static String computeGrade(String answer) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secretCode.length(); i++) {
            if (answer.charAt(i) == secretCode.charAt(i)) {
                ++bulls;
            } else {
                int currentIndex = i;
                if (secretCode.chars().anyMatch(ch -> ch == answer.charAt(currentIndex))) {
                    ++cows;
                }
            }
        }
        return getGradePresentation(bulls, cows);
    }

    private static String getGradePresentation(int bulls, int cows) {
        StringBuilder sb = new StringBuilder();
        if (bulls == 1) {
            sb.append("1 bull(s)");
        } else if (bulls > 1) {
            sb.append(bulls).append(" bull(s)");
        }
        if (cows > 0) {
            if (bulls > 0) {
                sb.append(" and ");
            }
            if (cows == 1) {
                sb.append("1 cow(s)");
            } else {
                sb.append(cows).append(" cow(s)");
            }
        }
        if (bulls == 0 && cows == 0) {
            sb.append("None");
        }
        return sb.toString();
    }
}
