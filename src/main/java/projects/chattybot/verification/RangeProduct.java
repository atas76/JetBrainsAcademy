package projects.chattybot.verification;

import java.util.Scanner;

public class RangeProduct {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        int product = 1;
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = a; i < b; i++) {
            product *= i;
        }
        System.out.println(product);
    }
}
