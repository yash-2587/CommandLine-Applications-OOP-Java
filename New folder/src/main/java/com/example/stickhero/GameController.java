package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController implements EventHandler<MouseEvent> {
    public static final int FPS = 100;
    private GameEngine engine;
    private Game panel;
    private boolean isIncreasing;
    private boolean isRunning;
    public boolean upsideDown;
    private ImageView btnSound;

    private ImageView btnMusic;

    public void init(GameEngine engine, Game panel) {
        this.engine = engine;
        this.panel = panel;
    }

    public void start() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                isRunning = true;
                while (isRunning) {
                    panel.render();
                    try {
                        Thread.sleep(1000 / GameController.FPS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }



    public void nextplatform(boolean ischerryEaten) {
        if (ischerryEaten)
            engine.cherryEaten();
        engine.nextPlat();
    }

    public void gameOver() {
        panel.gameOver();
    }
    public void save(){
        panel.goToSave();
    }

    public void replay() {
        init(engine, panel);
        panel.replay(engine, this);
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            mousePressed();
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
            mouseReleased();
        else if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
            upsideDown = !upsideDown;
    }


    public void mousePressed() {
        if (engine.isMoving()) {
            return;
        }

        Thread increaseStick = new Thread(() -> {
            isIncreasing = true;
            while (isIncreasing) {
                engine.increaseStickLength();
                try {
                    Thread.sleep(1000 / GameController.FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        increaseStick.start();
    }

    public void mouseReleased() {
        isIncreasing = false;
        engine.setMoving(true);
        engine.checkForGameOver();
    }

    public  void onExitButtonClick() {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent event) {
        panel.goToGame();
    }
    public void pause(MouseEvent event) {
        panel.gotopause();
    }

}