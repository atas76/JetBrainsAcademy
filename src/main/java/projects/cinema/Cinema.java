package projects.cinema;

import java.util.Scanner;

public class Cinema {

    private static final String TICKET_ALREADY_PURCHASED = "That ticket has already been purchased!";
    private static final String INVALID_INPUT = "Wrong input!";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int cols = scanner.nextInt();

        CinemaRoom cinemaRoom = new CinemaRoom(rows, cols);

        int menuChoice = -1;

        Statistics stats = new Statistics(rows * cols, new RevenueCalculator(rows, cols).doCalculate());

        while (menuChoice != 0) {
            System.out.println();
            System.out.print("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """);
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1 -> {
                    System.out.println();
                    cinemaRoom.displaySeats();
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("Enter a row number:");
                    int rowNum = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int seatNum = scanner.nextInt();

                    if (!cinemaRoom.isInputValid(rowNum, seatNum)) {
                        System.out.println(INVALID_INPUT);
                        continue;
                    };

                    if (!cinemaRoom.book(rowNum, seatNum)) {
                        System.out.println(TICKET_ALREADY_PURCHASED);
                        continue;
                    };

                    int ticketPrice = new PriceCalculator(rowNum, rows, cols).doCalculate();
                    System.out.println("Ticket price: $" + ticketPrice);

                    stats.addPurchasedTicket(ticketPrice);
                }
                case 3 -> stats.print();
            }
        }
    }

    private static void displayIncome(int rows, int cols) {
        System.out.println("Total income: ");
        System.out.println("$" + new RevenueCalculator(rows, cols).doCalculate());
    }
}
