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

    /*
    @Override
    public void init() throws Exception {
        Game game = new Game();
    }
     */
    @Override
    public void start(Stage window) {

        SetupView setup = new SetupView();

        BorderPane root = new BorderPane();

        //root.setCenter(play.getView());
        root.setCenter(setup.getView());
        setup.setOnSetupComplete(event -> {
            Game game;
            try {
                game = new Game(event.getWidth(), event.getHeight());
            } catch (IOException e) {
                System.out.println("Exception " + e);
                return;
            }

            PlayView play = new PlayView(game);
            root.setCenter(play.getView());
        });

        Scene scene = new Scene(root, 600, 700, Color.GREY);

        // window.setTitle("MINESWEEPER");

        /*  MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        menuBar.prefWidthProperty().bind(window.widthProperty());

        Menu fileMenu = new Menu("File");
        MenuItem newMI = new MenuItem("New");
        MenuItem loadMI = new MenuItem("Load");
        MenuItem saveMI = new MenuItem("Save");
        MenuItem exitMI = new MenuItem("Exit");
        
        
        
        exitMI.setOnAction(actionEvent -> Platform.exit());
        
        
        fileMenu.getItems().addAll(newMI, loadMI, saveMI, exitMI);

        Menu helpMenu = new Menu("Help");
        MenuItem rulesIM = new MenuItem("Rules");
        MenuItem aboutIM = new MenuItem("About minesweeper");
        //leads to wkipedia page about minesweeper
        helpMenu.getItems().addAll(rulesIM, aboutIM);

        menuBar.getMenus().addAll(fileMenu, helpMenu);
         */
        //root.setLeft(vbox);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(MinesweeperUi.class);
    }
}
