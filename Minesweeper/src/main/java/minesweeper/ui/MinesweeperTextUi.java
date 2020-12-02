package minesweeper.ui;

import java.io.IOException;
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
            System.out.print("Enter command (o = open, f = toggle a flag,"
                    + " s = save game): ");
            String command = reader.nextLine();

            if (command.toLowerCase().charAt(0) == 'f') {
                System.out.print("Enter y (0 - " + (game.getHeight() - 1) + "): ");
                y = Integer.valueOf(reader.nextLine());
                System.out.print("Enter x (0 - " + (game.getWidth() - 1) + "): ");
                x = Integer.valueOf(reader.nextLine());
                System.out.println("");
                game.setFlag(x, y, !game.isFlag(x, y));

            } else if (command.toLowerCase().charAt(0) == 'o') {
                System.out.print("Enter y (0 - " + (game.getHeight() - 1) + "): ");
                y = Integer.valueOf(reader.nextLine());
                System.out.print("Enter x (0 - " + (game.getWidth() - 1) + "): ");
                x = Integer.valueOf(reader.nextLine());
                System.out.println("");
                
                if (game.isFlag(x, y)) {
                    System.out.println("You cannot open a flagged square. "
                            + "Remove flag first.");
                    System.out.println("");
                } else {
                    game.open(x, y);
                }
            } else if (command.toLowerCase().charAt(0) == 's') {
                System.out.print("Enter filename: ");
                String fileName = reader.nextLine();
                try {
                    game.saveGameToFile(fileName);
                    
                } catch (IOException e) {
                    System.out.println("Could not save the game: " + e);
                }
            } else {
                System.out.println("Unknown command");
                System.out.println("");
            }

        } while (game.isRunning());

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
                } else if (game.isFlag(x, y)) {
                    System.out.print("F");
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
