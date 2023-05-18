package projects.numbers;

import java.util.*;

public class Number {

    private long number;

    private Map<String, Boolean> properties = new TreeMap<>();
    private static Set<String> supportedProperties =
            Set.of("even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping",
                    "happy", "sad");
    private static Map<String, String> mutuallyExclusiveProperties = Map.ofEntries(
        Map.entry("even", "odd"), Map.entry("odd", "even"),
        Map.entry("duck", "spy"), Map.entry("spy", "duck"),
        Map.entry("sunny", "square"), Map.entry("square", "sunny"),
        Map.entry("happy", "sad"), Map.entry("sad", "happy")
    );

    int [] digits;

    public Number(long number) {
        this(number, false);
    }

    public Number(long number, boolean intermediate) {

        this.number = number;
        this.digits = getDigits();

        if (intermediate) return;

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
        properties.put("jumping", isJumping());
        properties.put("happy", isHappy());
        properties.put("sad", !properties.get("happy"));
    }

    public static Set<String> getProperties() {
        return supportedProperties;
    }

    public static List<List<String>> findMutuallyExclusiveProperties(List<String> properties) {

        List<List<String>> mutuallyExclusiveProperties = new ArrayList<>();

        for (int i = 0; i < properties.size() - 1; i++) {
            for (int j = i + 1; j < properties.size(); j++) {
                if (areMutuallyExclusiveProperties(properties.get(i), properties.get(j))) {
                    mutuallyExclusiveProperties.add(Arrays.asList(properties.get(i), properties.get(j)));
                }
            }
        }
        return mutuallyExclusiveProperties;
    }

    public static List<List<String>> findMutuallyExcludedProperties(List<String> properties) {

        List<List<String>> retVal = new ArrayList<>();

        if (properties.contains("odd") && properties.contains("even")) {
            retVal.add(Arrays.asList("odd", "even"));
        }
        if (properties.contains("happy") && properties.contains("sad")) {
            retVal.add(Arrays.asList("happy", "sad"));
        }
        return retVal;
    }

    public static List<List<String>> findMutuallyExclusiveProperties(List<String> properties, List<String> excludedProperties) {

        List<List<String>> mutuallyExclusiveProperties = new ArrayList<>();

        for (int i = 0; i < properties.size(); i++) {
            for (int j = 0; j < excludedProperties.size(); j++) {
                if (properties.get(i).equals(excludedProperties.get(j))) {
                    mutuallyExclusiveProperties.add(Arrays.asList(properties.get(i), "-" + excludedProperties.get(j)));
                }
            }
        }
        return mutuallyExclusiveProperties;
    }

    public static boolean areMutuallyExclusiveProperties(String key, String value) {
        if (mutuallyExclusiveProperties.get(key) == null) return false;
        return mutuallyExclusiveProperties.get(key).equals(value);
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
        System.out.println("jumping: " + properties.get("jumping"));
        System.out.println("happy: " + properties.get("happy"));
        System.out.println("sad: " + properties.get("sad"));
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
            case "jumping" -> isJumping();
            case "happy" -> isHappy();
            case "sad" -> !isHappy();
            default -> false;
        };
    }

    int[] getDigits() {

        List<Integer> digits = new ArrayList<>();

        long quotient = number;

        while (quotient > 0) {
            digits.add( (int) quotient % 10);
            quotient /= 10;
        }

        return digits.stream().mapToInt(i -> i).toArray();
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

    private boolean isJumping() {
        for (int i = 0; i < digits.length - 1; i++) {
            if (Math.abs(digits[i] - digits[i + 1]) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isHappy() {
        List<Long> sequence = new ArrayList<>();
        long squaredDigitsSum = getSquaredDigitsSum(number);
        while (squaredDigitsSum != 1 && squaredDigitsSum != number && !sequence.contains(squaredDigitsSum)) {
            sequence.add(squaredDigitsSum);
            squaredDigitsSum = getSquaredDigitsSum(squaredDigitsSum);
        }
        return squaredDigitsSum == 1;
    }

    private long getSquaredDigitsSum(long number) {
        int [] digits = new Number(number, true).getDigits();
        long squaredDigitsSum = 0;
        for (int i = 0; i < digits.length; i++) {
            squaredDigitsSum += (long) digits[i] * digits[i];
        }
        return squaredDigitsSum;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
