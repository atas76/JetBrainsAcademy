package projects.tictactoe;

public class Game {

    enum State {
        NOT_FINISHED("Game not finished"),
        DRAW("Draw"),
        X_WIN("X wins"),
        O_WIN("O wins"),
        IMPOSSIBLE("Impossible");

        private String description;
        State(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }

    enum UpdateResult {
        SUCCESS,
        INVALID_INPUT("You should enter numbers!"),
        OCCUPIED_CELL("This cell is occupied! Choose another one!"),
        COORDINATES_OUT_OF_BOUNDS("Coordinates should be from 1 to 3!");

        private String errorMessage;

        UpdateResult() {}
        UpdateResult(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }
    }

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY_CELL = '_';

    private String grid;
    private char[][] matrix = new char[3][3];

    private char player = 'X';

    public Game(String grid) {
        this.grid = grid;
        for (int i = 0; i < 3; i++) {
            matrix[0][i] = this.grid.charAt(i);
        }
        for (int i = 3; i < 6; i++) {
            matrix[1][i - 3] = this.grid.charAt(i);
        }
        for (int i = 6; i < 9; i++) {
            matrix[2][i - 6] = this.grid.charAt(i);
        }
    }

    String getGrid() {
        return grid;
    }

    public void printGrid() {
        System.out.println("---------");
        printLine(0, 3);
        printLine(3, 6);
        printLine(6, 9);
        System.out.println("---------");
    }

    private void printLine(int begin, int end) {
        System.out.print("| ");
        for (int i = begin; i < end; i++) {
            System.out.print(grid.charAt(i) + " ");
        }
        System.out.println("|");
    }

    private boolean checkRow(int rowIndex, char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (matrix[rowIndex][i] != playerSymbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(char player) {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i, player)) return true;
        }
        return false;
    }

    private boolean checkColumn(int columnIndex, char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (matrix[i][columnIndex] != playerSymbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumns(char player) {
        for (int i = 0; i < 3; i++) {
            if (checkColumn(i, player)) return true;
        }
        return false;
    }

    private boolean checkLeftDiagonal(char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (matrix[i][i] != playerSymbol) return false;
        }
        return true;
    }

    private boolean checkRightDiagonal(char playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (matrix[i][2 - i] != playerSymbol) return false;
        }
        return true;
    }

    private boolean checkDiagonals(char player) {
        return checkLeftDiagonal(player) || checkRightDiagonal(player);
    }

    private boolean checkWinner(char player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    private boolean checkGameNotFinished() {
        return this.grid.indexOf(EMPTY_CELL) != -1;
    }

    private long count(char playerSymbol) {
        return this.grid.chars().filter(x -> x == playerSymbol).count();
    }

    public State evalGameState() {
        boolean gameNotFinished = checkGameNotFinished();
        boolean xWin = checkWinner(X);
        boolean oWin = checkWinner(O);
        // boolean draw = !(gameNotFinished || xWin || oWin);
        boolean impossible = xWin && oWin || (Math.abs(count(X) - count(O)) > 1);

        if (impossible) {
            return State.IMPOSSIBLE;
        } else if (oWin) {
            return State.O_WIN;
        } else if (xWin) {
            return State.X_WIN;
        } else if (gameNotFinished) {
            return State.NOT_FINISHED;
        } else {
            return State.DRAW;
        }
    }

    public UpdateResult updateGrid(int xInput, int yInput) {

        if (xInput < 1 || xInput > 3 || yInput < 1 || yInput > 3) {
            return UpdateResult.COORDINATES_OUT_OF_BOUNDS;
        }
        int x = xInput - 1;
        int y = yInput - 1;
        if (matrix[x][y] != EMPTY_CELL) {
            return UpdateResult.OCCUPIED_CELL;
        }
        updateCell(x, y);
        return UpdateResult.SUCCESS;
    }

    public void updateCell(int x, int y) {
        this.matrix[x][y] = player;
        int flatIndex = 3 * x + y;
        StringBuilder gridBuilder = new StringBuilder(this.grid);
        gridBuilder.setCharAt(flatIndex, player);
        this.grid = gridBuilder.toString();
        togglePlayer();
    }

    private void togglePlayer() {
        player = (player == 'X') ? 'O' : 'X';
    }
}
