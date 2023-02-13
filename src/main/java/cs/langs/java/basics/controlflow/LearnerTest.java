package cs.langs.java.basics.controlflow;

import java.util.Scanner;

public class LearnerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Yes!");
        } else if (choice <= 4) {
            System.out.println("No!");
        } else {
            System.out.println("Unknown number");
        }
    }
}
