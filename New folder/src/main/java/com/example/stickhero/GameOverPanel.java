package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverPanel extends Pane {
    private GameEngine engine;
    Button button;
    Button butback;

    public GameOverPanel(final GameEngine engine, final GameController controller) {
        this.engine = engine;
        button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(new ImageView(Images.replay));
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Audio.changeToMenu();
                controller.replay();
            }
        });

        ImageView imageView = new ImageView(Images.replay);
        imageView.setFitWidth(80); // Set the preferred width of the image
        imageView.setFitHeight(80); // Set the preferred height of the image
        imageView.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        button.setPrefSize(80, 80);
        button.setLayoutX(200);
        button.setLayoutY(400);
        button.setGraphic(imageView);
        getChildren().add(button);


        butback = new Button();
        ImageView imgback = new ImageView(Images.back);
        imgback.setFitWidth(80); // Set the preferred width of the image
        imgback.setFitHeight(80); // Set the preferred height of the image
        imgback.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butback.setStyle("-fx-background-color: transparent;");
        butback.setGraphic(imgback);

        butback.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Exit");
                controller.onExitButtonClick();
            }
        });
        butback.setPrefSize(80, 80);
        butback.setLayoutX( 300 );
        butback.setLayoutY(400);
        getChildren().add(butback);

        Button buthome = new Button();
        ImageView imghome = new ImageView(Images.home);
        imghome.setFitWidth(80); // Set the preferred width of the image
        imghome.setFitHeight(80); // Set the preferred height of the image
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
        buthome.setPrefSize(80, 80);
        buthome.setLayoutX( 100 );
        buthome.setLayoutY(400);
        getChildren().add(buthome);

        setPrefSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);
        setBackground(new Background(new BackgroundImage(Images.background, null, null, BackgroundPosition.CENTER, new BackgroundSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT, true, true, true, true))));


        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Times New Roman", 75));
        gameOverText.setFill(Color.BLACK);
        gameOverText.setLayoutX(38);
        gameOverText.setLayoutY(120);
        getChildren().add(gameOverText);

        Text scoreText = new Text("SCORE : " + engine.getScore());
        scoreText.setFont(Font.font("Times New Roman", 60));
        scoreText.setFill(Color.BLACK);
        scoreText.setLayoutX(40);
        scoreText.setLayoutY(250);
        getChildren().add(scoreText);

        Text cherryText = new Text("CHERRY : " + engine.getcherryNum());
        cherryText.setFont(Font.font("Times New Roman", 60));
        cherryText.setFill(Color.BLACK);
        cherryText.setLayoutX(40);
        cherryText.setLayoutY(330);
        getChildren().add(cherryText);
    }
}
