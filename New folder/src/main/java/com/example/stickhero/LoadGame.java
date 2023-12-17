package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoadGame extends Pane{
    private final GameController controller;

    public LoadGame(GameController controller) {
        this.controller = controller;
        setBackground(new Background(new BackgroundImage(Images.background, null, null, BackgroundPosition.CENTER, new BackgroundSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT, true, true, true, true))));
        setPrefSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);

        VBox loadGameMenu = new VBox();
        loadGameMenu.setLayoutX(80);
        loadGameMenu.setLayoutY(0);
        loadGameMenu.setPrefSize(100, 160);
        loadGameMenu.setAlignment(Pos.CENTER);
        loadGameMenu.setSpacing(1);
        loadGameMenu.setPadding(new Insets(10, 0, 0, 0));

        // Text for "Load Game"
        Text loadGameText = new Text("Load Game");
        loadGameText.setFont(Font.font("Times New Roman",82));
        loadGameText.setFill(Color.BLACK);

        loadGameMenu.getChildren().addAll(loadGameText);
        getChildren().add(loadGameMenu);

        Button button = new Button("Save 1");
        button.setStyle("-fx-background-color: transparent; -fx-font-size: 50;-fx-font-family: 'Times New Roman';");


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.actionPerformed(event);
            }
        });

        button.setPrefSize(250, 250);
        button.setLayoutX(0); // Use double for layout
        button.setLayoutY(75); // Use double for layout
        getChildren().add(button);

        Button button2 = new Button("Save 2");
        button2.setStyle("-fx-background-color: transparent; -fx-font-size: 50;-fx-font-family: 'Times New Roman';");


        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.actionPerformed(event);
            }
        });

        button2.setPrefSize(250, 250);
        button2.setLayoutX(0); // Use double for layout
        button2.setLayoutY(150); // Use double for layout
        getChildren().add(button2);

        Button button3 = new Button("Save 3");
        button3.setStyle("-fx-background-color: transparent; -fx-font-size: 50;-fx-font-family: 'Times New Roman';");


        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.actionPerformed(event);
            }
        });

        button3.setPrefSize(250, 250);
        button3.setLayoutX(0); // Use double for layout
        button3.setLayoutY(225); // Use double for layout
        getChildren().add(button3);

        Button button4 = new Button("Save 4");
        button4.setStyle("-fx-background-color: transparent; -fx-font-size: 50;-fx-font-family: 'Times New Roman';");


        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.actionPerformed(event);
            }
        });

        button4.setPrefSize(250, 250);
        button4.setLayoutX(0); // Use double for layout
        button4.setLayoutY(300); // Use double for layout
        getChildren().add(button4);

        Button cancel = new Button("Cancel");
        cancel.setStyle("-fx-background-color: transparent; -fx-font-size: 60;-fx-font-family: 'Times New Roman';");


        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.replay();
            }
        });

        cancel.setPrefSize(250, 250);
        cancel.setLayoutX(90); // Use double for layout
        cancel.setLayoutY(400); // Use double for layout
        getChildren().add(cancel);


    }
}