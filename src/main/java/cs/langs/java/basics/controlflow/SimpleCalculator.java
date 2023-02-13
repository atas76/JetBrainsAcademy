package cs.langs.java.basics.controlflow;

import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        long operand1 = scanner.nextLong();
        String operator = scanner.next();
        long operand2 = scanner.nextLong();
        long result = 0;
        String error = "";

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) {
                    error = "Division by 0!";
                    break;
                }
                result = operand1 / operand2;
                break;
            default:
                error = "Unknown operator";
                break;
        }
        if (!error.isEmpty()) {
            System.out.println(error);
        } else {
            System.out.println(result);
        }
    }
}
