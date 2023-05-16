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

    private static void cleanupPropertyInput() {
        properties = new ArrayList<>();
        wrongProperties = new ArrayList<>();
        mutuallyExclusiveProperties = new ArrayList<>();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("* the first parameter represents a starting number;");
        System.out.println("* the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
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
                if (properties.isEmpty()) {
                    if (numberInput > 0) {
                        for (long i = numberInput; i < numberInput + inputOffset; i++) {
                            new Number(i).printSummary();
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
                            if (properties.stream().anyMatch(currentNumber::hasProperty)) {
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
                                wrongProperties.forEach(property -> System.out.println("The property [" + property.toUpperCase() + "] is wrong."));
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
                        if (!Number.getProperties().contains(property)) {
                            wrongProperties.add(property);
                        } else {
                            properties.add(property);
                        }
                    }
                    mutuallyExclusiveProperties = Number.findMutuallyExclusiveProperties(properties);
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
