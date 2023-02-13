package projects.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void evalGameStateXwinEmptyCells() {
        Game game = new Game("XXXOO__O_");

        assertEquals(Game.State.X_WIN, game.evalGameState());
    }

    @Test
    void evalGameStateXwinFilledGrid() {
        Game game = new Game("XOXOXOXXO");

        assertEquals(Game.State.X_WIN, game.evalGameState());
    }

    @Test
    void evalGameStateOwin() {
        Game game = new Game("XOOOXOXXO");

        assertEquals(Game.State.O_WIN, game.evalGameState());
    }

    @Test
    void evalGameStateDraw() {
        Game game = new Game("XOXOOXXXO");

        assertEquals(Game.State.DRAW, game.evalGameState());
    }

    @Test
    void evalGameStateNotFinished() {
        Game game = new Game("XO_OOX_X_");

        assertEquals(Game.State.NOT_FINISHED, game.evalGameState());
    }

    @Test
    void evalGameImpossibleBothWin() {
        Game game = new Game("XO_XO_XOX");

        assertEquals(Game.State.IMPOSSIBLE, game.evalGameState());
    }

    @Test
    void evalGameImpossibleTooManyX() {
        Game game = new Game("_O_X__X_X");

        assertEquals(Game.State.IMPOSSIBLE, game.evalGameState());
    }

    @Test
    void evalGameImpossibleTooManyO() {
        Game game = new Game("_OOOO_X_X");

        assertEquals(Game.State.IMPOSSIBLE, game.evalGameState());
    }

    @Test
    void updateGrid1() {
        Game game = new Game("X_X_O____");

        Game.UpdateResult result = game.updateGrid(3, 1);

        assertEquals(Game.UpdateResult.SUCCESS, result);
        assertEquals('X', game.getGrid().charAt(6));
        game.printGrid();
    }

    @Test
    void upgradeGrid2() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(1, 1);

        assertEquals(Game.UpdateResult.SUCCESS, result);
        assertEquals('X', game.getGrid().charAt(0));
        game.printGrid();
    }

    @Test
    void upgradeGrid3() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(3, 3);

        assertEquals(Game.UpdateResult.SUCCESS, result);
        assertEquals('X', game.getGrid().charAt(8));
        game.printGrid();
    }

    @Test
    void upgradeGrid4() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(2, 3);

        assertEquals(Game.UpdateResult.SUCCESS, result);
        assertEquals('X', game.getGrid().charAt(5));
        game.printGrid();
    }

    @Test
    void upgradeGridOccupiedCell() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(3, 1);

        assertEquals(Game.UpdateResult.OCCUPIED_CELL, result);
    }

    @Test
    void upgradeGridCoordinateOutOfBoundsX() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(4, 1);

        assertEquals(Game.UpdateResult.COORDINATES_OUT_OF_BOUNDS, result);
    }

    @Test
    void upgradeGridCoordinateOutOfBoundsY() {
        Game game = new Game("_XXOO_OX_");

        Game.UpdateResult result = game.updateGrid(1, 4);

        assertEquals(Game.UpdateResult.COORDINATES_OUT_OF_BOUNDS, result);
    }
}
