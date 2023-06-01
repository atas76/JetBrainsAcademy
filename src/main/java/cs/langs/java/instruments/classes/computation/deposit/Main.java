package cs.langs.java.instruments.classes.computation.deposit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String startingAmountStr = scanner.next();
        double interestRate = scanner.nextDouble();
        int years = scanner.nextInt();

        BigDecimal startingAmount = new BigDecimal(startingAmountStr);
        BigDecimal yearlyIncrease = BigDecimal.ONE;
        BigDecimal interestRateBigDecimal = new BigDecimal(interestRate);
        interestRateBigDecimal = interestRateBigDecimal.divide(new BigDecimal(100));
        yearlyIncrease = yearlyIncrease.add(interestRateBigDecimal);
        BigDecimal increase = yearlyIncrease.pow(years);
        BigDecimal finalAmount = startingAmount.multiply(increase);
        finalAmount = finalAmount.setScale(2, RoundingMode.CEILING);

        System.out.println("Amount of money in the account: " + finalAmount);
    }
}
