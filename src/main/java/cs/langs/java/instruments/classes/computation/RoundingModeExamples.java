package cs.langs.java.instruments.classes.computation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingModeExamples {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("100.5649");
        System.out.println(bigDecimal.setScale(3, RoundingMode.CEILING));     // 100.565
        System.out.println(bigDecimal.setScale(3, RoundingMode.DOWN));     // 100.564
        System.out.println(bigDecimal.setScale(3, RoundingMode.FLOOR));     // 100.564

        bigDecimal = new BigDecimal("0.55");
        System.out.println(bigDecimal.setScale(1, RoundingMode.HALF_DOWN));   // 0.5
        System.out.println(bigDecimal.setScale(1, RoundingMode.HALF_EVEN));   // 0.6
        System.out.println(bigDecimal.setScale(3, RoundingMode.UNNECESSARY)); // 0.550

    }
}
