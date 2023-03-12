package projects.bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static String secretCode;
    private static Random rnd = new Random();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");
        int codeDigitsNum = scanner.nextInt();

        if (codeDigitsNum <= 10) {
            secretCode = generateSecretCode(codeDigitsNum);
            // System.out.println("The random secret number is " + secretCode);
        } else {
            System.out.println("Error: can't generate a secret number with a length of " + codeDigitsNum
                    + " because there aren't enough unique digits.");
            System.exit(1);
        }

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

    private static String generateSecretCode(int codeDigitsNum) {

        StringBuilder secretNumberBuilder = new StringBuilder(10);

        while (secretNumberBuilder.length() < codeDigitsNum) {

            int currentDigit = rnd.nextInt(10);

            if (currentDigit == 0 && secretNumberBuilder.isEmpty()) continue;
            if (!secretNumberBuilder.toString().contains(String.valueOf(currentDigit))) {
                secretNumberBuilder.append(currentDigit);
            }
        }
        return secretNumberBuilder.toString();
    }

    /*
    private static String generateSecretCode(int codeDigitsNum) {

        StringBuilder secretNumberBuilder = new StringBuilder(10);

        while (secretNumberBuilder.length() < codeDigitsNum) {
            long pseudoRandomNumber = System.nanoTime();
            String pseudoRandomNumberStr = String.valueOf(pseudoRandomNumber);

            for (int i = pseudoRandomNumberStr.length() - 1; i >= 0; i--) {
                char currentChar = pseudoRandomNumberStr.charAt(i);
                if (currentChar == '0' && secretNumberBuilder.isEmpty()) continue;
                if (!secretNumberBuilder.toString().contains(Character.toString(currentChar))) {
                    secretNumberBuilder.append(currentChar);
                }
                if (secretNumberBuilder.length() == codeDigitsNum) break;
            }
        }
        return secretNumberBuilder.toString();
    }
     */

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
