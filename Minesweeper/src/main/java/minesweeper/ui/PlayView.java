package minesweeper.ui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import minesweeper.domain.Game;

public class PlayView {

    private Game game;
    private Button[][] buttons;
    private Label gameStatus;
    private StartOverEventHandler startOverHandler;

    public PlayView(Game game) {
        this.game = game;
        this.buttons = new Button[game.getHeight()][game.getWidth()];
    }

    public Parent getView() {

        BorderPane root = new BorderPane();
        GridPane minefield = new GridPane();
        VBox rightPane = new VBox();

        gameStatus = new Label("");
        Button startOver = new Button("Start over");
        startOver.setOnAction(event -> {
            startOverHandler.handle(new StartOverEvent());
        });

        rightPane.getChildren().addAll(gameStatus, startOver);

        root.setCenter(minefield);
        root.setRight(rightPane);

        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                Button b = new Button();
                final int x2 = x;
                final int y2 = y;
                b.setOnMouseClicked((event) -> {
                    if (game.isRunning()) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (!game.isFlag(x2, y2)) {
                                game.open(x2, y2);
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            game.setFlag(x2, y2, !game.isFlag(x2, y2));
                        }
                        updateView();
                    }

                });

                minefield.add(b, x, y);
                buttons[y][x] = b;
            }
        }
        updateView();

        return root;
    }

    private void updateView() {
        if (!game.isRunning()) {
            
            if (game.isVictory()) {
                gameStatus.setText("You won the game!");
            } else {
                gameStatus.setText("GAME OVER.\nYou stepped on a mine.");
            }
        }
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                String label = "?";
                if (game.isOpen(x, y)) {
                    int count = game.countMines(x, y);
                    if (count > 0) {
                        label = String.valueOf(count);
                    } else {
                        label = " ";
                    }
                } else if (game.isFlag(x, y)) {
                    label = "F";
                }
                if (!game.isRunning() && game.isMine(x, y)) {
                    label = "*";
                }
                buttons[y][x].setText(label);
            }
        }
    }
    
    public void setOnStartOver(StartOverEventHandler handler) {
        this.startOverHandler = handler;
    }
}
