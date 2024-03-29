package projects.numbers;

import java.util.*;

public class Main {

    private static String input;
    private static String [] inputArray;
    private static int inputOffset;
    private static long numberInput;
    private static List<String> properties = new ArrayList<>();
    private static List<String> wrongProperties = new ArrayList<>();
    private static List<List<String>> mutuallyExclusiveProperties = new ArrayList<>();
    private static List<String> excludedProperties = new ArrayList<>();

    private static void cleanupPropertyInput() {
        properties = new ArrayList<>();
        wrongProperties = new ArrayList<>();
        mutuallyExclusiveProperties = new ArrayList<>();
        excludedProperties = new ArrayList<>();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("* the first parameter represents a starting number;");
        System.out.println("* the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
        System.out.println("Enter a request: "); readInput(scanner);

        while (numberInput != 0) {
            if (inputOffset == 0) {
                if (numberInput > 0) {
                    new Number(numberInput).print();
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            if (inputOffset > 0) {
                if (properties.isEmpty() && wrongProperties.isEmpty() && mutuallyExclusiveProperties.isEmpty()) {
                    if (numberInput > 0) {
                        if (excludedProperties.isEmpty()) {
                            for (long i = numberInput; i < numberInput + inputOffset; i++) {
                                new Number(i).printSummary();
                            }
                        } else {
                            List<Long> propertySatisfyingNumbers = new ArrayList<>();
                            int numberOffset = 0;
                            int numbersFound = 0;
                            while (numbersFound < inputOffset) {
                                Number currentNumber = new Number(numberInput + numberOffset);
                                if (excludedProperties.stream().noneMatch(currentNumber::hasProperty)) {
                                    propertySatisfyingNumbers.add(numberInput + numberOffset);
                                    ++numbersFound;
                                }
                                ++numberOffset;
                            }
                            propertySatisfyingNumbers.forEach(num -> new Number(num).printSummary());
                        }
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                } else {
                    if (numberInput > 0) {
                        List<Long> propertySatisfyingNumbers = new ArrayList<>();
                        int numberOffset = 0;
                        int numbersFound = 0;
                        while (numbersFound < inputOffset) {
                            Number currentNumber = new Number(numberInput + numberOffset);
                            if (properties.stream().allMatch(currentNumber::hasProperty) &&
                                    excludedProperties.stream().noneMatch(currentNumber::hasProperty)) {
                                propertySatisfyingNumbers.add(numberInput + numberOffset);
                                ++numbersFound;
                            }
                            ++numberOffset;
                        }
                        propertySatisfyingNumbers.forEach(num -> new Number(num).printSummary());
                    } else {
                        switch ((int) numberInput) {
                            case -1:
                                System.out.println("The first parameter should be a natural number or zero.");
                            case -2:
                                if (wrongProperties.size() == 1) {
                                    System.out.println("The property [" + wrongProperties.get(0).toUpperCase() + "] is wrong.");
                                }
                                if (wrongProperties.size() > 1) {
                                    StringBuilder propertySetString = new StringBuilder();
                                    propertySetString.append("[");
                                    wrongProperties.forEach(property -> {
                                        propertySetString.append(property.toUpperCase());
                                        propertySetString.append(", ");
                                    });
                                    propertySetString.replace(propertySetString.length() - 2, propertySetString.length(), "]");
                                    System.out.println("The properties " + propertySetString + " are wrong.");
                                }
                                if (!wrongProperties.isEmpty()) Number.printProperties();
                                mutuallyExclusiveProperties.forEach(propertyPair -> {
                                    System.out.println("The request contains mutually exclusive properties: [" +
                                            propertyPair.get(0).toUpperCase() + ", " + propertyPair.get(1).toUpperCase() + "]");
                                    System.out.println("There are no numbers with these properties.");
                                });
                                break;
                            default:
                                System.out.println("Unspecified error");
                        }
                    }
                }
            }
            if (inputOffset < 0) {
                System.out.println("The second parameter should be a natural number.");
            }
            System.out.println();
            System.out.println("Enter a request: "); readInput(scanner);
        }

        System.out.println();
        System.out.println("Goodbye!");
    }

    private static void readInput(Scanner scanner) {
        cleanupPropertyInput();
        input = scanner.nextLine();
        inputArray = input.split("\s");
        inputOffset = 0;
        try {
            numberInput = Long.parseLong(inputArray[0]);
        } catch(NumberFormatException nfex) {
            numberInput = -1;
            return;
        }
        if (inputArray.length > 1) {
            try {
                inputOffset = Integer.parseInt(inputArray[1]);
                if (inputArray.length > 2) {
                    for (int i = 2; i < inputArray.length; i++) {
                        String property = inputArray[i].toLowerCase();
                        if (property.startsWith("-")) {
                            excludedProperties.add(property.substring(1));
                        }
                        if (!Number.getProperties().contains(property) && !property.startsWith("-")) {
                            wrongProperties.add(property);
                        } else if (!property.startsWith("-")) {
                            properties.add(property);
                        } else {
                            if (!Number.getProperties().contains(property.substring(1))) {
                                wrongProperties.add(property);
                            }
                        }
                    }
                    mutuallyExclusiveProperties = Number.findMutuallyExclusiveProperties(properties);
                    var mutuallyExcludedProperties =
                            Number.findMutuallyExcludedProperties(excludedProperties);
                    mutuallyExcludedProperties.forEach(propertyPair -> {
                        propertyPair.set(0, "-" + propertyPair.get(0));
                        propertyPair.set(1, "-" + propertyPair.get(1));
                    });
                    mutuallyExclusiveProperties.addAll(mutuallyExcludedProperties);
                    mutuallyExclusiveProperties.addAll(
                            Number.findMutuallyExclusiveProperties(properties, excludedProperties));
                    if (!wrongProperties.isEmpty() || !mutuallyExclusiveProperties.isEmpty()) {
                        numberInput = -2;
                    }
                }
            } catch (NumberFormatException nfex) {
                inputOffset = -1;
            }
         }
    }
}
