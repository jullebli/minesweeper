package minesweeper.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class MinesweeperUi extends Application {

    @Override
    public void start(Stage window) {

        SetupView setup = new SetupView(window);

        BorderPane root = new BorderPane();

        //root.setCenter(play.getView());
        root.setCenter(setup.getView());
        setup.setOnSetupComplete(event -> {
            
            PlayView play = new PlayView(event.getGame());
            play.setOnStartOver(event2 -> {
                root.setCenter(setup.getView());
            });
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
