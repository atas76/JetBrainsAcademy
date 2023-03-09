package projects.bullscows;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static String code;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StringBuilder code = new StringBuilder(10);

        int numberOfDigits = scanner.nextInt();

        if (numberOfDigits <= 10) {
            while (code.length() < numberOfDigits) {
                long pseudoRandomNumber = System.nanoTime();
                String pseudoRandomNumberStr = String.valueOf(pseudoRandomNumber);
                pseudoRandomNumberStr.chars()
                        .mapToObj(i -> (char) i)
                        .sorted(Collections.reverseOrder()).limit(numberOfDigits - code.length())
                        .forEach(code::append);
            }
            System.out.println("The random secret number is " + code);
        } else {
            System.out.println("Error: can't generate a secret number with a length of " + numberOfDigits
                    + " because there aren't enough unique digits.");
        }

        // Stage 2
        // code = String.valueOf(9305);
        // System.out.println("Grade: " + computeGrade(scanner.next()) + ". The secret code is " + code);
    }

    private static void gameFlowPrototype() {
        System.out.println("The secret code is prepared: ****.");
        System.out.println();
        playTurn("1234", 1);
        System.out.println();
        boolean correctResult = playTurn("9876", 2);
        if (correctResult)
            System.out.printf("Congrats! The secret code is " + code + ".");
    }

    private static boolean playTurn(String answer, int ordinal) {
        System.out.println("Turn " + ordinal + ". Answer:");
        System.out.println(answer);
        String grade = computeGrade(answer);
        System.out.println("Grade: " + grade + ".");
        return "4 bulls".equals(grade);
    }

    private static String computeGrade(String answer) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == code.charAt(i)) {
                ++bulls;
            } else {
                int currentIndex = i;
                if (code.chars().anyMatch(ch -> ch == answer.charAt(currentIndex))) {
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
