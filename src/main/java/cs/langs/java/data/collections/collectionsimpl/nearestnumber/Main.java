package cs.langs.java.data.collections.collectionsimpl.nearestnumber;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String numbersInput = scanner.nextLine();
        List<Integer> numbers = Arrays.stream(numbersInput.split("\s")).map(Integer::valueOf).toList();
        int referenceNumber = scanner.nextInt();

        int minDiff = Integer.MAX_VALUE;
        for (int number: numbers) {
            int currentDiff = Math.abs(referenceNumber - number);
            if (currentDiff <= minDiff) {
                minDiff = currentDiff;
            }
        }

        List<Integer> nearestDifferenceNumbers = new ArrayList<>();
        for (int number: numbers) {
            int currentDiff = Math.abs(referenceNumber - number);
            if (currentDiff == minDiff) {
                nearestDifferenceNumbers.add(number);
            }
        }

        Collections.sort(nearestDifferenceNumbers);

        StringBuilder result = new StringBuilder();
        nearestDifferenceNumbers.forEach(number -> result.append(number).append(" "));

        System.out.println(result.toString().trim());
    }
}
