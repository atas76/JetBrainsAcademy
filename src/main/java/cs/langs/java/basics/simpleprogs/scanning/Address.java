package cs.langs.java.basics.simpleprogs.scanning;

import java.util.Scanner;

public class Address {

    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        String streetName = scanner.next();
        int streetNumber = scanner.nextInt();
        scanner.next();
        int apartmentNumber = scanner.nextInt();
        scanner.nextLine();
        String city = scanner.nextLine();
        String country = scanner.nextLine();
        String postCode = scanner.nextLine();

        System.out.println("Street name: " + streetName);
        System.out.println("Street number: " + streetNumber);
        System.out.println("Apartment number: " + apartmentNumber);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
        System.out.println("Postcode: " + postCode);
    }
}
