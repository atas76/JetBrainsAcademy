package cs.langs.java.instruments.classes.computation.rounding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.next();
        int precision = scanner.nextInt();

        BigDecimal bigDecimal = new BigDecimal(number);
        bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_DOWN);
        System.out.println(bigDecimal);
    }
}
