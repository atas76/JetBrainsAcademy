package projects.minesweeper;

import java.util.Random;

public class Minefield {

    private static final Random rnd = new Random();
    private final boolean [][] minefield = new boolean[X_SIZE][Y_SIZE];
    private static final int X_SIZE = 9;
    private static final int Y_SIZE = 9;

    public Minefield(int minesNum) {
        layMines(minesNum);
    }

    public void display() {
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                System.out.print(minefield[i][j] ? "X" : ".");
            }
            System.out.println();
        }
    }

    private void layMines(int minesNum) {
        int counter = 0;

        while (counter < minesNum) {
            int x = rnd.nextInt(X_SIZE);
            int y = rnd.nextInt(Y_SIZE);
            if (!minefield[x][y]) {
                minefield[x][y] = true;
                counter++;
            }
        }
    }
}
