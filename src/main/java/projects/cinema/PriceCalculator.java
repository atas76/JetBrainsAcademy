package projects.cinema;

import static projects.cinema.RevenueCalculator.*;

public class PriceCalculator {

    private int row;
    private int rows;
    private int cols;

    public PriceCalculator(int row, int rows, int cols) {
        this.row = row;
        this.rows = rows;
        this.cols = cols;
    }

    public int doCalculate() {
        final int capacity = rows * cols;

        if (capacity <= CAPACITY_THRESHOLD) {
            return SMALL_ROOM_PRICE;
        } else {
            return row <= rows / 2 ? FRONT_SEATS_PRICE : BACK_SEATS_PRICE;
        }
    }
}
