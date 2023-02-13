package projects.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);

        Game game = new Game(scanner.next());
        game.printGrid();

        Game.UpdateResult updateResult;

        while (game.evalGameState() != Game.State.NOT_FINISHED) {
            try {
                int xInput = scanner.nextInt();
                int yInput = scanner.nextInt();
                updateResult = game.updateGrid(xInput, yInput);
                if (updateResult != Game.UpdateResult.SUCCESS) {
                    System.out.println(updateResult.getErrorMessage());
                } else {
                    game.printGrid();
                }
            } catch (InputMismatchException ex) {
                System.out.println(Game.UpdateResult.INVALID_INPUT.getErrorMessage());
                scanner.nextLine();
            }
        }

        System.out.println(game.evalGameState().getDescription());
    }
}
