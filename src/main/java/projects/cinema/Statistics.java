package projects.cinema;

public class Statistics {

    private final int totalTickets;
    private int purchasedTickets;
    private double purchasedTicketsPercentage;
    private int currentIncome;
    private final int totalIncome;

    public void print() {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", purchasedTicketsPercentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public Statistics(int totalTickets, int totalIncome) {
        this.totalTickets = totalTickets;
        this.totalIncome = totalIncome;
    }

    public void addPurchasedTicket(int price) {
        ++purchasedTickets;
        currentIncome += price;
        purchasedTicketsPercentage = purchasedTickets * 100.0 / totalTickets;
    }
}
