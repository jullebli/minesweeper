package minesweeper.ui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SetupView {

    private SetupCompleteEventHandler setupCompleteHandler;

    public Parent getView() {

        BorderPane root = new BorderPane();

        VBox vbox = new VBox();
        HBox hboxHeight = new HBox();
        HBox hboxWidth = new HBox();

        Label label = new Label("Select size of the minefield");
        Label widthLabel = new Label("Width:");
        Label heightLabel = new Label("Height:");
        TextField width = new TextField("10");
        TextField height = new TextField("10");

        hboxWidth.getChildren().addAll(widthLabel, width);
        hboxHeight.getChildren().addAll(heightLabel, height);

        Button start = new Button("Start");
        start.setOnAction(event -> {
            int playWidth = Integer.valueOf(width.getText());
            int playHeight = Integer.valueOf(height.getText());

            setupCompleteHandler.handle(new SetupCompleteEvent(playWidth,
                    playHeight));
        });

        vbox.getChildren().add(label);
        vbox.getChildren().add(hboxWidth);
        vbox.getChildren().add(hboxHeight);
        vbox.getChildren().add(start);

        root.setCenter(vbox);

        return root;
        
    }

    public void setOnSetupComplete(SetupCompleteEventHandler handler) {
        this.setupCompleteHandler = handler;
    }
}
