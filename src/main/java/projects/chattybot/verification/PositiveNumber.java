package projects.chattybot.verification;

import java.util.Scanner;

public class PositiveNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        System.out.println(scanner.nextInt() > 0 ? "YES" : "NO");
    }
}
