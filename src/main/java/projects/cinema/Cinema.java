package projects.cinema;

import java.util.Scanner;

public class Cinema {

    private final static int ROWS = 7;
    private final static int COLS = 8;

    private static int rows = ROWS;
    private static int cols = COLS;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows: ");
        System.out.print("> ");
        rows = scanner.nextInt();

        System.out.println("Enter number of columns: ");
        System.out.print("> ");
        cols = scanner.nextInt();

        System.out.println("Total income: ");
        System.out.println("$" + new RevenueCalculator(rows, cols).doCalculate());
    }

    private static void displayLayout() {
        // Write your code here
        System.out.print("""
                          Cinema:
                            1 2 3 4 5 6 7 8
                           """);
        for (int i = 1; i <= ROWS; i++) {
            System.out.print(i);
            for (int j = 1; j <= COLS; j++) {
                System.out.print(" S");
            }
            System.out.println();
        }
    }
}
