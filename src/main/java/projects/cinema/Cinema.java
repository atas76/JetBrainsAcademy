package projects.cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int cols = scanner.nextInt();

        CinemaRoom cinemaRoom = new CinemaRoom(rows, cols);
        cinemaRoom.displaySeats();

        System.out.println("Enter a row number:");
        int rowNum = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();

        System.out.println("Ticket price: $" + new PriceCalculator(rowNum, rows, cols).doCalculate());

        cinemaRoom.book(rowNum, seatNum);
        cinemaRoom.displaySeats();
    }

    private static void displayIncome(int rows, int cols) {
        System.out.println("Total income: ");
        System.out.println("$" + new RevenueCalculator(rows, cols).doCalculate());
    }
}
