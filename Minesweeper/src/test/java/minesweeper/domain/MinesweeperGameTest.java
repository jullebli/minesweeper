package minesweeper.domain;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperGameTest {

    private Game game;

    @Before
    public void setUp() throws IOException {

        game = new Game(new String[]{
            "...M.MMM",
            ".....M.M",
            ".....MMM",
            "........",
            "........",
            "...M....",
            "........",
            "F......M"
        });
    }

    @Test
    public void heightIsCorrect() throws IOException {
        Game game2 = new Game(8, 10);
        assertEquals(game2.getHeight(), 10);
    }

    @Test
    public void widthIsCorrect() throws IOException {
        Game game2 = new Game(8, 10);
        assertEquals(game2.getWidth(), 8);
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
    public void isMineWorks() {
        assertTrue(game.isMine(3, 5));
    }
    
    @Test
    public void isMineIsFalseWhenNoMine() {
        assertFalse(game.isMine(0, 0));
    }

    @Test
    public void whenRunningThenIsRunningReturnsTrue() {
        assertTrue(game.isRunning());
    }

    @Test
    public void whenOpensMineThenIsRunningReturnsFalse() {
        game.open(3, 5);
        assertFalse(game.isRunning());
    }
    
    @Test
    public void whenOpensMineThenVictoryIsFalse() {
        game.open(3, 5);
        assertFalse(game.isVictory());
    }

    @Test
    public void openWorksOnOneSquare() {
        game.open(0, 0);
        assertTrue(game.isOpen(0, 0));
    }

    @Test
    public void openOpensAlsoNeighbour() {
        game.open(1, 0);
        assertTrue(game.isOpen(0, 0));
    }

    @Test
    public void victoryIsFalseInTheBeginning() {
        assertFalse(game.isVictory());
    }
    
    @Test
    public void victoryIsPossible() {
        game.open(0, 0);
        game.open(6, 1);
        game.open(4, 0);
        assertTrue(game.isVictory());
    }
    
    @Test
    public void victoryEndsTheGame() {
        game.open(0, 0);
        game.open(6, 1);
        game.open(4, 0);
        assertFalse(game.isRunning());
    }

    @Test
    public void countMinesReturns8() {
        assertEquals(game.countMines(6, 1), 8);
    }

    @Test
    public void isFlagIsTrueWhenThereIsAFlag() {
        assertTrue(game.isFlag(0, 7));
    }

    @Test
    public void isFlagIsFalseWhenFlagIsRemoved() {
        game.setFlag(0, 7, false);
        assertFalse(game.isFlag(0, 7));
    }

    @Test
    public void setFlagWorks() {
        game.setFlag(3, 3, true);
        assertTrue(game.isFlag(3, 3));
    }
    
    @Test
    public void saveAndLoadWorks() throws IOException {
        String filename = "junitTest.minesave"; 
               
        game.saveGameToFile(filename);
        Game game2 = new Game(filename);
        assertEquals(game.toString(), game2.toString());
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
                + "10000000\n";

        assertTrue(gameString.equals(game.toString()));
    }
}
