package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class PauseMenu extends StackPane { // Change AnchorPane to StackPane
    private GameController controller;

    public PauseMenu(GameController controller) {
        this.controller = controller;

        StackPane root = new StackPane(); // Use StackPane for overlapping panes

        // Your existing button and image view code...

        Text pausedText = new Text("Paused");
        pausedText.setFont(new Font(48.0));
        pausedText.setFill(javafx.scene.paint.Color.web("#14ff0c"));
        pausedText.setLayoutX(30.0);
        pausedText.setLayoutY(-10.0);

        root.getChildren().addAll(pausedText);
        getChildren().addAll(root);

        Button buthome = new Button();
        ImageView imghome = new ImageView(Images.home);
        imghome.setFitWidth(66); // Set the preferred width of the image
        imghome.setFitHeight(66); // Set the preferred height of the image
        imghome.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        buthome.setStyle("-fx-background-color: transparent;");
        buthome.setGraphic(imghome);
        buthome.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Audio.changeToMenu();
                controller.replay();
            }
        });
        buthome.setPrefSize(66, 66);
        buthome.setLayoutX( 0 );
        buthome.setLayoutY(0);

        Button butretry = new Button();
        ImageView imgretry = new ImageView(Images.home);
        imgretry.setFitWidth(66); // Set the preferred width of the image
        imgretry.setFitHeight(66); // Set the preferred height of the image
        imgretry.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butretry.setStyle("-fx-background-color: transparent;");
        butretry.setGraphic(imghome);
        butretry.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Audio.changeToMenu();
                controller.replay();
            }
        });
        butretry.setPrefSize(66, 66);
        butretry.setLayoutX( 0 );
        butretry.setLayoutY(0);

        Button butsave = new Button();
        ImageView imgsave = new ImageView(Images.save);
        imgsave.setFitWidth(66); // Set the preferred width of the image
        imgsave.setFitHeight(66); // Set the preferred height of the image
        imgsave.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butsave.setStyle("-fx-background-color: transparent;");
        butsave.setGraphic(imghome);
        butsave.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Audio.changeToMenu();
                controller.replay();
            }
        });
        butsave.setPrefSize(66, 66);
        butsave.setLayoutX( 0 );
        butsave.setLayoutY(0);



        root.getChildren().addAll(butretry, buthome, butsave);

    }


}
