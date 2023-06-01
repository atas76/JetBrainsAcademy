package cs.langs.java.instruments.classes.computation.roundpower;

import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int power = scanner.nextInt();
        int scale = scanner.nextInt();

        System.out.println(scanner.nextBigDecimal().setScale(scale, RoundingMode.FLOOR).pow(power));
    }
}
