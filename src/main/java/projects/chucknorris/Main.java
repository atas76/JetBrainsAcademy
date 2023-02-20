package projects.chucknorris;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input string:");
        String input = scanner.nextLine();

        System.out.println("The result:");
        input.chars().forEach(ch -> System.out.println((char) ch + " = " +
                String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0')));
    }

    private static void printSpaced(String input) {
        StringBuilder sb = new StringBuilder();
        input.chars().mapToObj(c -> (char) c).forEach(ch -> sb.append(ch).append(" "));
        System.out.println(sb);
    }
}
