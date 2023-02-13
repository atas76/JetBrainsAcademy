package projects.chattybot.verification;

import java.util.Scanner;

public class DivisibleNumbers {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int sum = 0;
        int cardinality = scanner.nextInt();
        for (int i = 0; i < cardinality; i++) {
            int number = scanner.nextInt();
            if (number % 6 == 0) {
                sum += number;
            }
        }
        System.out.println(sum);
    }
}
