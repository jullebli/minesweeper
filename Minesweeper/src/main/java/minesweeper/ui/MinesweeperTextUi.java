package minesweeper.ui;

import java.util.Scanner;
import minesweeper.domain.Game;

public class MinesweeperTextUi {

    private Scanner reader;

    public MinesweeperTextUi(Scanner reader) {
        this.reader = reader;
    }

    public void start() {
        System.out.println("Enter height: ");
        int height = Integer.valueOf(reader.nextLine());
        System.out.println("Enter width: ");
        int width = Integer.valueOf(reader.nextLine());
        System.out.println("");

        Game game = new Game(width, height);
        
        int x, y;

        do {
            showBoard(game);
            System.out.println("Open square.");
            System.out.println("Enter y (0 - " + (game.getHeight() - 1) + "):");
            y = Integer.valueOf(reader.nextLine());
            System.out.println("Enter x (0 - " + (game.getWidth() - 1) + "):");
            x = Integer.valueOf(reader.nextLine());
            System.out.println("");

        } while (game.open(x, y));

        if (game.isVictory()) {
            System.out.println("You won the game");
        } else {
            System.out.println("You lost, you stepped on a mine");
        }
    }

    public void showBoard(Game game) {
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                if (game.isOpen(x, y)) {
                    System.out.print(game.countMines(x, y));
                } else {
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        MinesweeperTextUi textUi = new MinesweeperTextUi(reader);
        textUi.start();
    }
}
