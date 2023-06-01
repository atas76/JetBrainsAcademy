package cs.langs.java.instruments.classes.computation.goldmining;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal dwalin = scanner.nextBigDecimal();
        BigDecimal balin = scanner.nextBigDecimal();
        BigDecimal thorin = scanner.nextBigDecimal();

        System.out.println(dwalin.add(balin).add(thorin));
    }
}
