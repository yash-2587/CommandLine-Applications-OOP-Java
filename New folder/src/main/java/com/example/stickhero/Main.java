package com.example.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;

public class Main extends Application implements Serializable {
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        Thread audio = new com.example.stickhero.Audio();
        audio.start();
        Main.stage = primaryStage;
        primaryStage.setTitle("Stick Hero");
        stage.getIcons().add(Images.icon);
        startGame();

        Main.stage.setResizable(false);
        stage.show();

    }
    public static void startGame(){
        GameEngine engine = new GameEngine();
        GameController controller = new GameController();
        Game panel = new Game();

        engine.init();
        controller.init(engine, panel);
        panel.init(engine, controller);

        controller.start();
        scene = new Scene(panel);
        stage.setScene(scene);
    }
}
