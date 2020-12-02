package minesweeper.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperGameTest {

    private Game game;

    @Before
    public void setUp() {

        game = new Game(new String[]{
            "...M.MMM",
            ".....M.M",
            ".....MMM",
            "........",
            "........",
            "...M....",
            "........",
            ".......M"
        });
    }

    @Test
    public void heightIs8() {
        assertEquals(game.getHeight(), 8);
    }

    @Test
    public void widthIs8() {
        assertEquals(game.getWidth(), 8);
    }

    @Test
    public void whenRunningThenIsRunningReturnsTrue() {
        assertTrue(game.isRunning());
    }

    @Test
    public void whenOpensMineThenIsRunningReturnsFalse() {
        game.open(3, 5); //has a mine x = 3, y = 5
        assertFalse(game.isRunning());
    }
    
    @Test
    public void openWorksOnOneSquare() {
        game.open(0, 0);
        assertTrue(game.isOpen(0, 0));
    }
    
    @Test
    public void openOpensAlsoNeighbour() {
        game.open(1,0);
        assertTrue(game.isOpen(0, 0));
    }

    @Test
    public void victoryIsFalseInTheBeginning() {
        assertFalse(game.isVictory());
    }

    @Test
    public void countMinesReturns8() {
        assertEquals(game.countMines(6, 1), 8);
    }

    @Test
    public void toStringIsSameAsTestGame() {

        String gameString = "Minesweepersavegame\n"
                            + "1\n"
                            + "8\n"
                            + "8\n"
                            + "00010111\n"
                            + "00000101\n"
                            + "00000111\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00010000\n"
                            + "00000000\n"
                            + "00000001\n"
                            + "8\n"
                            + "8\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "8\n"
                            + "8\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n"
                            + "00000000\n";

        assertTrue(gameString.equals(game.toString()));
    }
}
