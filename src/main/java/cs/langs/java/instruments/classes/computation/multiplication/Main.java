package cs.langs.java.instruments.classes.computation.multiplication;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal input1 = scanner.nextBigDecimal();
        BigDecimal input2 = scanner.nextBigDecimal();

        System.out.println(input1.multiply(input2));
    }
}
