package minesweeper.ui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import minesweeper.domain.Game;

public class PlayView {

    private Game game;
    private Button[][] buttons;
    private Label gameStatus;
    private StartOverEventHandler startOverHandler;
    private Stage stage;

    public PlayView(Game game, Stage stage) {
        this.game = game;
        this.buttons = new Button[game.getHeight()][game.getWidth()];
        this.stage = stage;
    }

    public Parent getView() {

        BorderPane root = new BorderPane();
        GridPane minefield = new GridPane();
        VBox rightPane = new VBox();

        gameStatus = new Label("");
        Button startOver = new Button("Start over");
        startOver.setOnAction(event -> {
            if (game.isRunning()) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Minesweeper");
                alert.setHeaderText("You are about to start a new game but "
                        + "the current game is still running");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() != ButtonType.OK) {
                    return;
                }
            }
            startOverHandler.handle(new StartOverEvent());
        });

        Button saveGame = new Button("Save game");
        saveGame.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter formatFilter
                    = new FileChooser.ExtensionFilter("Minesweeper saved game",
                            "*" + Game.SAVEGAME_EXTENSION);
            fileChooser.getExtensionFilters().add(formatFilter);
            fileChooser.setTitle("Open Minesweeper saved game");
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile == null) {
                return;
            }

            String fileName = selectedFile.getName();
            if (!fileName.endsWith(Game.SAVEGAME_EXTENSION)) {
                fileName += Game.SAVEGAME_EXTENSION;
            }

            try {
                game.saveGameToFile(fileName);
            } catch (IOException e) {
                System.out.println("Could not save the game");
            }
        });

        Button quit = new Button("Quit");
        quit.setOnAction(event -> {
            if (game.isRunning()) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Minesweeper");
                alert.setHeaderText("You are about to quit the game but it "
                        + "is still running");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() != ButtonType.OK) {
                    return;
                }
            }
            Platform.exit();
        });

        gameStatus.setMaxWidth(Double.MAX_VALUE);
        startOver.setMaxWidth(Double.MAX_VALUE);
        saveGame.setMaxWidth(Double.MAX_VALUE);
        quit.setMaxWidth(Double.MAX_VALUE);

        rightPane.getChildren().addAll(gameStatus, startOver, saveGame, quit);

        root.setCenter(minefield);
        root.setRight(rightPane);

        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                Button b = new Button();
                b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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
