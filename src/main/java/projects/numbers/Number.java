package projects.numbers;

import java.util.*;

public class Number {

    private long number;

    private Map<String, Boolean> properties = new TreeMap<>();

    public Number(long number) {

        this.number = number;

        properties.put("even", number % 2 == 0) ;
        properties.put("odd", number % 2 != 0);

        boolean divisibleBySeven = (number % 7 == 0);
        boolean lastDigitSeven = number % 10 == 7;
        properties.put("buzz", divisibleBySeven || lastDigitSeven);

        properties.put("duck", isDuck());
        properties.put("palindromic", isPalindromic());
    }

    public void print() {
        System.out.println("Properties of " + number);
        System.out.println("even: " + properties.get("even"));
        System.out.println("odd: " + properties.get("odd"));
        System.out.println("buzz: " + properties.get("buzz"));
        System.out.println("duck: " + properties.get("duck"));
        System.out.println("palindromic: " + properties.get("palindromic"));
    }

    public void printSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        sb.append(" is ");
        properties.forEach((property, value) -> { if (properties.get(property)) sb.append(property).append(", "); });

        System.out.println(sb.substring(0, sb.length() - 2));
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
}
