package projects.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
            String numberInput = scanner.next();
            if ("/exit".equals(numberInput)) {
                break;
            }
            if ("/back".equals(numberInput)) {
                System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
                input = scanner.next();
                if ("/exit".equals(input)) {
                    System.exit(0);
                }
                sourceBase = Integer.parseInt(input);
                targetBase = scanner.nextInt();
                continue;
            }

            if (sourceBase == targetBase) {
                System.out.println("Conversion result: " + numberInput);
                continue;
            }

            BigDecimal result = BigDecimal.ZERO;

            String integerPart = numberInput;
            String fractionalPart = "";
            String convertedFractionalPart = "";
            if (numberInput.contains(".")) {
                String [] numberParts = numberInput.split("\\.");
                integerPart = numberParts[0];
                fractionalPart = numberParts[1];
            }

            if (sourceBase == 10) {
                BigInteger decimalNumberIntegerPart = new BigInteger(integerPart);
                if (!fractionalPart.isEmpty()) {
                    fractionalPart = "0." + fractionalPart;
                    BigDecimal decimalNumberFractionalPart = new BigDecimal(fractionalPart);
                    convertedFractionalPart = convertFromDecFractionalPart(targetBase, decimalNumberFractionalPart);
                }
                System.out.println("Conversion result: " +
                        convertFromDecIntegerPart(targetBase, decimalNumberIntegerPart) +
                                (!convertedFractionalPart.isEmpty() ? "." + convertedFractionalPart : ""));
                System.out.println();
            } else if (sourceBase < 10) {
                for (int i = 0; i < integerPart.length(); i++) {
                    double currentDigit = (integerPart.charAt(i) - '0') * Math.pow(sourceBase, integerPart.length() - i - 1);
                    result = result.add(BigDecimal.valueOf((long) currentDigit));
                }
                for (int i = 0; i < fractionalPart.length(); i++) {
                    double currentDigit = (fractionalPart.charAt(i) - '0') / Math.pow(sourceBase, i + 1);
                    result = result.add(BigDecimal.valueOf(currentDigit));
                }
            } else {
                for (int i = 0; i < integerPart.length(); i++) {
                    double currentDigit = convertFromAlphabeticalDigit(integerPart.charAt(i)) *
                            Math.pow(sourceBase, integerPart.length() - i - 1);
                    result = result.add(BigDecimal.valueOf((long) currentDigit));
                }
                for (int i = 0; i < fractionalPart.length(); i++) {
                    double currentDigit = convertFromAlphabeticalDigit(fractionalPart.charAt(i)) /
                            Math.pow(sourceBase, i + 1);
                    result = result.add(BigDecimal.valueOf(currentDigit));
                }
                if (!fractionalPart.isEmpty()) {
                    result = result.setScale(5, RoundingMode.HALF_UP);
                }
            }
            if (sourceBase != 10) {
                if (targetBase == 10) {
                    if (!fractionalPart.isEmpty()) {
                        result = result.setScale(5, RoundingMode.HALF_UP);
                    }
                    System.out.println("Conversion result: " + result);
                    System.out.println();
                } else {
                    BigDecimal resultFractionalPart = result.remainder(BigDecimal.ONE);
                    String fractionalPartStr = "";
                    if (resultFractionalPart.compareTo(BigDecimal.ZERO) != 0) {
                        fractionalPartStr = convertFromDecFractionalPart(targetBase, resultFractionalPart);
                    } else if (!fractionalPart.isEmpty()) {
                        fractionalPartStr = "00000";
                    }
                    System.out.println("Conversion result: " +
                            convertFromDecIntegerPart(targetBase, result.toBigInteger()) +
                            (!fractionalPartStr.isEmpty() ? "." + fractionalPartStr : ""));
                    System.out.println();
                }
            }
        }
    }

    private static String convertFromDecFractionalPart(int targetBase, BigDecimal decimalFractionalPart) {
        StringBuilder resultSb = new StringBuilder();
        BigDecimal targetBaseBigDec = BigDecimal.valueOf(targetBase);
        int precisionCounter = 0;
        while (!decimalFractionalPart.equals(BigDecimal.ZERO)) {
            if (precisionCounter >= 5) break;
            BigDecimal currentNumber = decimalFractionalPart.multiply(targetBaseBigDec);
            decimalFractionalPart = currentNumber.remainder(BigDecimal.ONE);
            BigDecimal currentDigit = currentNumber.subtract(decimalFractionalPart);
            String currentDigitStr = convertFromDecIntegerPart(targetBase, currentDigit.toBigInteger());
            resultSb.append(currentDigitStr);
            ++precisionCounter;
        }
        if (resultSb.isEmpty()) resultSb.append("0");
        return resultSb.toString();
    }

    private static String convertFromDecIntegerPart(int targetBase, BigInteger decimalNumber) {
        StringBuilder resultSb = new StringBuilder();
        BigInteger targetBaseBigInt = BigInteger.valueOf(targetBase);
        while (!decimalNumber.equals(BigInteger.ZERO)) {
            resultSb.append(Character.forDigit(decimalNumber.mod(BigInteger.valueOf(targetBase)).intValue(),
                    targetBase));
            decimalNumber = decimalNumber.divide(targetBaseBigInt);
        }
        if (resultSb.isEmpty()) resultSb.append("0");
        return resultSb.reverse().toString();
    }

    private static int convertFromAlphabeticalDigit(char digit) {
        return (digit >= 65) ? 10 + Character.toLowerCase(digit) - 'a' : digit - '0';
    }
}
