package projects.chattybot.personalize;

import java.util.Scanner;

public class TensDigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        String number = scanner.next();
        System.out.println(number.charAt(number.length() - 2));
    }
}
