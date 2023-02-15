package projects.cinema;

public class RevenueCalculator {

    private int rows;
    private int cols;

    final static int CAPACITY_THRESHOLD = 60;
    final static int SMALL_ROOM_PRICE = 10;
    final static int FRONT_SEATS_PRICE = 10;
    final static int BACK_SEATS_PRICE = 8;

    public RevenueCalculator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int doCalculate() {
        final int capacity = rows * cols;

        if (capacity <= CAPACITY_THRESHOLD) {
            return calculateSmallRoomRevenue(capacity);
        } else {
            return calculateLargeRoomRevenue();
        }
    }

    private int calculateSmallRoomRevenue(int capacity) {
        return capacity * SMALL_ROOM_PRICE;
    }

    private int calculateLargeRoomRevenue() {
        int frontRows = rows / 2;
        int backRows = rows - frontRows;

        return (frontRows * FRONT_SEATS_PRICE + backRows * BACK_SEATS_PRICE) * cols;
    }
}
