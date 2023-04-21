package projects.numbers;

import java.util.*;

public class Number {

    private long number;

    private Map<String, Boolean> properties = new TreeMap<>();
    private static Set<String> supportedProperties = Set.of("even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny");

    public Number(long number) {

        this.number = number;

        properties.put("even", number % 2 == 0) ;
        properties.put("odd", number % 2 != 0);

        boolean divisibleBySeven = (number % 7 == 0);
        boolean lastDigitSeven = number % 10 == 7;
        properties.put("buzz", divisibleBySeven || lastDigitSeven);

        properties.put("duck", isDuck());
        properties.put("palindromic", isPalindromic());
        properties.put("gapful", isGapful());
        properties.put("spy", isSpy());
        properties.put("sunny", isSunny());
        properties.put("square", isPerfectSquare());
    }

    public static Set<String> getProperties() {
        return supportedProperties;
    }

    public static void printProperties() {
        StringBuilder sb = new StringBuilder();
        supportedProperties.forEach(property -> sb.append(property.toUpperCase()).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        System.out.println("Available properties: [" + sb + "]");
    }

    public void print() {
        System.out.println("Properties of " + number);
        System.out.println("even: " + properties.get("even"));
        System.out.println("odd: " + properties.get("odd"));
        System.out.println("buzz: " + properties.get("buzz"));
        System.out.println("duck: " + properties.get("duck"));
        System.out.println("palindromic: " + properties.get("palindromic"));
        System.out.println("gapful: " + properties.get("gapful"));
        System.out.println("spy: " + properties.get("spy"));
        System.out.println("sunny: " + properties.get("sunny"));
        System.out.println("square: " + properties.get("square"));
    }

    public void printSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        sb.append(" is ");
        properties.forEach((property, value) -> { if (properties.get(property)) sb.append(property).append(", "); });

        System.out.println(sb.substring(0, sb.length() - 2));
    }

    public boolean hasProperty(String property) {
        return switch(property) {
            case "even" -> number % 2 == 0;
            case "odd" ->  number % 2 != 0;
            case "buzz" -> (number % 7 == 0) || number % 10 == 7;
            case "duck" -> isDuck();
            case "palindromic" -> isPalindromic();
            case "gapful" -> isGapful();
            case "spy" -> isSpy();
            case "square" -> isPerfectSquare();
            case "sunny" -> isSunny();
            default -> false;
        };
    }

    private boolean isDuck() {

        long quotient = number;

        while (quotient > 0) {
            long modulo = quotient % 10;
            if (modulo == 0) return true;
            quotient /= 10;
        }

        return false;
    }

    private boolean isPalindromic() {
        long quotient = number;
        long reverseNumber = 0;
        while (quotient > 0) {
            long modulo = quotient % 10;
            reverseNumber = reverseNumber * 10 + modulo;
            quotient /= 10;
        }
        return reverseNumber == this.number;
    }

    private boolean isGapful() {

        if (number < 100) return false;

        long quotient = number / 10;
        long modulo = number % 10;
        long lastDigit = modulo;

        while (quotient > 0) {
            modulo = quotient % 10;
            quotient /= 10;
        }

        long firstDigit = modulo;
        long divisor = firstDigit * 10 + lastDigit;

        return (number % divisor == 0);
    }

    private boolean isSpy() {

        long quotient = number / 10;
        long modulo = number % 10;
        long sum = modulo;
        long product = modulo;

        while (quotient > 0) {
            modulo = quotient % 10;
            sum += modulo;
            product *= modulo;
            quotient /= 10;
        }

        return sum == product;
    }

    private boolean isPerfectSquare() {
        return isPerfectSquare(this.number);
    }

    private boolean isPerfectSquare(long number) {
        return (long) Math.sqrt(number) * (long) Math.sqrt(number) == number;
    }

    private boolean isSunny() {
        return isPerfectSquare(this.number + 1);
    }
}
