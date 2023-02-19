package projects.chucknorris;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Input string:");
        String input = scanner.nextLine();

        input.chars().mapToObj(c -> (char) c).forEach(ch -> sb.append(ch).append(" "));
        System.out.println(sb);
    }
}
