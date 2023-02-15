package projects.cinema;

public class CinemaRoom {

    private final static int ROWS = 7;
    private final static int COLS = 8;

    private int rows = ROWS;
    private int cols = COLS;

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

    private Seat[][] seatArrangement = new Seat[rows][cols];

    public CinemaRoom(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seatArrangement[i][j] = Seat.AVAILABLE;
            }
        }
    }

    public void book(int row, int seat) {
        seatArrangement[row - 1][seat - 1] = Seat.OCCUPIED;
    }

    public void displaySeats() {
        System.out.print("""
                          Cinema:
                            1 2 3 4 5 6 7 8
                           """);
        for (int i = 1; i <= ROWS; i++) {
            System.out.print(i);
            for (int j = 1; j <= COLS; j++) {
                System.out.print(" " + seatArrangement[i - 1][j - 1]);
            }
            System.out.println();
        }
    }
}
