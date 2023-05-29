package projects.converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
        String input = scanner.next();
        if ("/exit".equals(input)) {
            System.exit(0);
        }
        int sourceBase = Integer.parseInt(input);
        int targetBase = scanner.nextInt();

         while (true) {

            System.out.println("Enter number in base " + sourceBase + " to convert to base " +
                    targetBase + " (To go back type /back)");
            String number = scanner.next();
            if ("/exit".equals(number)) {
                break;
            }
            if ("/back".equals(number)) {
                System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
                input = scanner.next();
                if ("/exit".equals(input)) {
                    System.exit(0);
                }
                sourceBase = Integer.parseInt(input);
                targetBase = scanner.nextInt();
                continue;
            }

            BigInteger result = BigInteger.ZERO;

            if (sourceBase == 10) {
                BigInteger decimalNumber = new BigInteger(number);
                System.out.println("Conversion result: " + convertFromDec(targetBase, decimalNumber));
                System.out.println();
            } else if (sourceBase < 10) {
                for (int i = 0; i < number.length(); i++) {
                    double currentDigit = (number.charAt(i) - '0') * Math.pow(sourceBase, number.length() - i - 1);
                    result = result.add(BigInteger.valueOf((long) currentDigit));
                }
            } else {
                for (int i = 0; i < number.length(); i++) {
                    double currentDigit = convertFromAlphabeticalDigit(number.charAt(i)) *
                            Math.pow(sourceBase, number.length() - i - 1);
                    result = result.add(BigInteger.valueOf((long) currentDigit));
                }
            }
            if (sourceBase != 10) {
                if (targetBase == 10) {
                    System.out.println("Conversion result: " + result);
                    System.out.println();
                } else {
                    System.out.println("Conversion result: " + convertFromDec(targetBase, result));
                    System.out.println();
                }
            }
        }
    }

    private static String convertFromDec(int targetBase, BigInteger decimalNumber) {
        StringBuilder resultSb = new StringBuilder();
        BigInteger targetBaseBigInt = BigInteger.valueOf(targetBase);
        while (!decimalNumber.equals(BigInteger.ZERO)) {
            resultSb.append(Character.forDigit(decimalNumber.mod(BigInteger.valueOf(targetBase)).intValue(),
                    targetBase));
            decimalNumber = decimalNumber.divide(targetBaseBigInt);
        }
        return resultSb.reverse().toString();
    }

    private static int convertFromAlphabeticalDigit(char digit) {
        return (digit >= 65) ? 10 + Character.toLowerCase(digit) - 'a' : digit - '0';
    }
}
