package projects.cinema;

public class CinemaRoom {

    private int rows;
    private int cols;

    enum Seat {
        AVAILABLE("S"), OCCUPIED("B");

        private String label;

        Seat(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    private Seat[][] seatArrangement;

    public CinemaRoom(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.seatArrangement = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seatArrangement[i][j] = Seat.AVAILABLE;
            }
        }
    }

    public boolean isInputValid(int row, int seat) {
        return row <= rows && seat <= cols && row > 0 && seat > 0;
    }

    public boolean book(int row, int seatNumber) {
        if (seatArrangement[row - 1][seatNumber - 1] == Seat.OCCUPIED) {
            return false;
        }
        seatArrangement[row - 1][seatNumber - 1] = Seat.OCCUPIED;
        return true;
    }

    public void displaySeats() {
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 0; i < cols; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 1; i <= rows; i++) {
            System.out.print(i);
            for (int j = 1; j <= cols; j++) {
                System.out.print(" " + seatArrangement[i - 1][j - 1]);
            }
            System.out.println();
        }
    }
}
