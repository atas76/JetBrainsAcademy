package projects.readablityscore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        if (text.length() > 100) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }
}
