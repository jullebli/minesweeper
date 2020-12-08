package minesweeper.ui;

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

public class MinesweeperUi extends Application {

    /*
    @Override
    public void init() throws Exception {
        Game game = new Game();
    }
     */
    @Override
    public void start(Stage window) {
        
        // Work in progresss, not in use yet
        
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 600, 700, Color.GREY);

        window.setTitle("MINESWEEPER");

        VBox vbox = new VBox();

        vbox.getChildren().add(new Label("Easy"));
        vbox.getChildren().add(new Label("8x8 board"));
        vbox.getChildren().add(new Label("10 mines"));

        //
        HBox easyMediumHbox = new HBox();

        easyMediumHbox.getChildren().add(new Button("Easy\\n8x8 board\\n10 mines"));
        easyMediumHbox.getChildren().add(new Button("Medium\\16x16 board\\n40 mines"));

        MenuBar menuBar = new MenuBar();
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

        //root.setLeft(vbox);
        root.setLeft(easyMediumHbox);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(MinesweeperUi.class);
    }
}
