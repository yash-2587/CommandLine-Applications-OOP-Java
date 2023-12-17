package com.example.stickhero;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.*;

public class Game extends BorderPane {
    @Serial
    private static final long serialVersionUID = 1L;
    private static Game instance;
    private PlayPanel playPanel;
    private MainMenu mainMenu;
    private LoadGame savePanel;
    private GameOverPanel gameOverPanel;
    private PauseMenu pause;
    private GameEngine engine;
    private GameController controller;
    public  static Scene scene;

    public void init(GameEngine engine, GameController controller) {
        this.engine = engine;
        this.controller = controller;

        mainMenu = new MainMenu(controller);
        playPanel = new PlayPanel(engine, controller);
        savePanel = new LoadGame(controller);
        pause= new PauseMenu(controller);
        playPanel.setOnMouseClicked(controller);
        playPanel.setOnMousePressed(controller);
        playPanel.setOnMouseReleased(controller);

        setCenter(mainMenu);

    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    public void goToGame() {
        setCenter(playPanel);
    }

    public void gameOver() {
        gameOverPanel = new GameOverPanel(engine, controller);
        getChildren().clear();
        setCenter(gameOverPanel);
    }
    public void goToSave() {
        savePanel = new LoadGame(controller);
        getChildren().clear();
        setCenter(savePanel);
    }

    public void replay(GameEngine engine, GameController controller) {
        Main.startGame();
        setCenter(playPanel);

    }
    public void home(GameEngine engine, GameController controller){

    }

    public void render() {
        goToGame();
    }

    public void gotopause() {
        pause = new PauseMenu(controller);
        setCenter(pause);

    }
    public void serialize(int i) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./saves/save" + i + ".txt"));
            out.writeObject(this);
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            if(out != null)
                out.close();
        }

    }

    public static Game deserialize(int i) throws IOException,ClassNotFoundException {
        ObjectInputStream in = null;
        Game game = null;
        try {
            in = new ObjectInputStream(new FileInputStream("saves/save" + i + ".txt"));
            game = (Game) in.readObject();
        }catch(Exception e){
            System.out.println("game not found");
        }finally {
            if(in != null)
                in.close();
        }
        return game;
    }
}
