package projects.minesweeper;

import java.util.Random;

public class Minefield {

    private static Random rnd = new Random();
    private boolean [][] minefield = new boolean[X_SIZE][Y_SIZE];
    private static int NUMBER_OF_MINES = 10;
    private static int X_SIZE = 9;
    private static int Y_SIZE = 9;

    public Minefield() {
        layMines();
    }

    public void display() {
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                System.out.print(minefield[i][j] ? "X" : ".");
            }
            System.out.println();
        }
    }

    private void layMines() {
        int counter = 0;

        while (counter < NUMBER_OF_MINES) {
            int x = rnd.nextInt(X_SIZE);
            int y = rnd.nextInt(Y_SIZE);
            if (!minefield[x][y]) {
                minefield[x][y] = true;
                counter++;
            }
        }
    }
}
