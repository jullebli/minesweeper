package minesweeper.ui;

import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import minesweeper.domain.Game;

public class SetupView {

    private SetupCompleteEventHandler setupCompleteHandler;
    private Stage stage;

    public SetupView(Stage stage) {
        this.stage = stage;
    }

    public Parent getView() {

        BorderPane root = new BorderPane();

        VBox vbox = new VBox();
        HBox hboxHeight = new HBox();
        HBox hboxWidth = new HBox();

        Label label = new Label("Select size of the minefield:");
        Label widthLabel = new Label("Width:");
        Label heightLabel = new Label("Height:");
        TextField width = new TextField("10");
        TextField height = new TextField("10");
        Button loadGame = new Button("Load saved game");
        loadGame.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter formatFilter
                    = new FileChooser.ExtensionFilter("Minesweeper saved game",
                            "*" + Game.SAVEGAME_EXTENSION);
            fileChooser.getExtensionFilters().add(formatFilter);
            fileChooser.setTitle("Open Minesweeper saved game");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile == null) {
                return;
            }
            Game game;
            try {
                game = new Game(selectedFile.getName());
            } catch (IOException e) {
                System.out.println("Exception " + e);
                return;
            }

            setupCompleteHandler.handle(new SetupCompleteEvent(game));

        });

        hboxWidth.getChildren().addAll(widthLabel, width);
        hboxHeight.getChildren().addAll(heightLabel, height);

        Button start = new Button("Start");
        start.setOnAction(event -> {
            int playWidth = Integer.valueOf(width.getText());
            int playHeight = Integer.valueOf(height.getText());

            Game game;
            try {
                game = new Game(playWidth, playHeight);
            } catch (IOException e) {
                System.out.println("Exception " + e);
                return;
            }

            setupCompleteHandler.handle(new SetupCompleteEvent(game));
        });

        vbox.getChildren().add(label);
        vbox.getChildren().add(hboxWidth);
        vbox.getChildren().add(hboxHeight);
        vbox.getChildren().add(start);
        vbox.getChildren().add(loadGame);

        root.setCenter(vbox);

        return root;

    }

    public void setOnSetupComplete(SetupCompleteEventHandler handler) {
        this.setupCompleteHandler = handler;
    }
}
