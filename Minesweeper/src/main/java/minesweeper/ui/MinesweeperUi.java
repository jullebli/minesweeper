package minesweeper.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import minesweeper.domain.Game;

public class MinesweeperUi extends Application {

    @Override
    public void start(Stage window) {

        SetupView setup = new SetupView();

        BorderPane root = new BorderPane();

        //root.setCenter(play.getView());
        root.setCenter(setup.getView());
        setup.setOnSetupComplete(event -> {
            
            PlayView play = new PlayView(event.getGame());
            root.setCenter(play.getView());
        });

        Scene scene = new Scene(root, 600, 700, Color.GREY);

        window.setTitle("Minesweeper");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(MinesweeperUi.class);
    }
}
