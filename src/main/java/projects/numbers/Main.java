package projects.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static String input;
    private static String [] inputArray;
    private static int inputOffset;
    private static long numberInput;
    private static String currentProperty;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("enter two natural numbers to obtain the properties of the list:");
        System.out.println("* the first parameter represents a starting number;");
        System.out.println("* the second parameter shows how many consecutive numbers are to be processed;");
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
                if (currentProperty.isEmpty()) {
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
                            if (new Number(numberInput + numberOffset).hasProperty(currentProperty)) {
                                propertySatisfyingNumbers.add(numberInput + numberOffset);
                                ++numbersFound;
                            }
                            ++numberOffset;
                        }
                        propertySatisfyingNumbers.forEach(num -> new Number(num).printSummary());
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
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
        input = scanner.nextLine();
        inputArray = input.split("\s");
        inputOffset = 0;
        currentProperty = "";
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
                    currentProperty = inputArray[2];
                    Set<String> properties = Number.getProperties();
                    if (!properties.contains(currentProperty)) {
                        numberInput = -1;
                        System.out.println("The property [" + currentProperty.toUpperCase() + "] is wrong.");
                        Number.printProperties();
                    }
                }
            } catch (NumberFormatException nfex) {
                inputOffset = -1;
            }
         }
    }
}
