package cs.langs.java.basics.simpleprogs.scanning;

import java.util.Scanner;

public class Greeting {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please introduce yourself: ");
        String name = scanner.nextLine();

        System.out.println("Hello, " + name + "!");
    }
}
