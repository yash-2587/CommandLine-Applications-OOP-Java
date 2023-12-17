package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;


public class PlayPanel extends Pane {
    private static final int stickwidth = 3;
    private static final int platheight = 220;
    private static final int platstart = 50;

    private GameEngine engine;
    private GameController controller;
//    private PauseMenu pause;

    private int backgroundMoveValue = 0;

    private int firstWidth;
    private int secondWidth;
    private int secondplatpos;
    private int moveValue;

    private int rotateDegree;
    private int rotateSpeed;

    private int dest;
    private int x;
    private int y;
    private int i;
    private int count;

    private boolean ischerryeaten;

    private Image heroImage;


    public PlayPanel(GameEngine engine, GameController controller) {
        this.engine = engine;
        this.controller = controller;
        init();
        loadImages();
        startGameLoop();
    }

    private void init() {
        backgroundMoveValue++;
        moveValue = 0;
        secondplatpos = 600;

        rotateDegree = 0;
        rotateSpeed = 1;

        firstWidth = engine.getFirstRect().getWidth();
        secondWidth = engine.getSecondRect().getWidth();

        x = platstart + firstWidth - 5 - Images.HeroWidth;
        y = Images.BACKGROUND_HEIGHT - platheight - Images.HeroHeight;

        i = 0;
        count = 0;
        dest = 0;

        ischerryeaten = false;

    }

    private void loadImages() {
        heroImage = Images.hero;
    }

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        moveBackground();
        calcplatmove();
        calcDegree();
        calcDest();
        move();
        CherryEaten();
        GameOver();
    }

    private void moveBackground() {
        setBackground(new Background(new BackgroundImage(Images.background, null, null, BackgroundPosition.CENTER, new BackgroundSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT, true, true, true, true))));
        if (rotateDegree == 90 && x == dest && backgroundMoveValue % 20 != 0 && !engine.isGameOver())
            backgroundMoveValue++;
    }

    private void calcplatmove() {
        if (moveValue >= engine.getDistance() + firstWidth) {
            controller.nextplatform(ischerryeaten);
            init();
        }

        if (rotateDegree == 90 && x == dest && !engine.isGameOver()) {
            moveValue += 4;
        }
    }

    private void calcDegree() {
        if (!engine.isMoving()) {
            return;
        }

        if (rotateDegree < 90) {
            rotateDegree += rotateSpeed / 5;
            rotateSpeed++;
        } else {
            rotateDegree = 90;
        }
    }

    private void calcDest() {
        if (engine.isGameOver() && controller.isUpsideDown()) {
            return;
        }

        if (engine.isGameOver()) {
            dest = platstart + firstWidth - Images.HeroWidth + engine.getStickLength();
        } else {
            dest = platstart + firstWidth + engine.getDistance() + secondWidth - 5 - Images.HeroWidth;
        }
    }

    private void move() {
        if (rotateDegree == 90 && x < dest) {
            x += 2;
        }

        if (x > dest) {
            x = dest;
        }

        if (x == dest && engine.isGameOver()) {
            y += 20;
        }

        if (y > Images.BACKGROUND_HEIGHT) {
                engine.setGameOver(true);
                controller.gameOver();
        }
    }

    public void render() {
        Canvas canvas = new Canvas(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);
        GraphicsContext g2d = canvas.getGraphicsContext2D();

        g2d.save();

        double pivotX = platstart + firstWidth - stickwidth - 2;
        double pivotY = Images.BACKGROUND_HEIGHT - platheight;
        Affine oldTransform = g2d.getTransform();
        Affine rotateTransform = new Affine();
        rotateTransform.appendTranslation(pivotX, pivotY);
        rotateTransform.appendRotation(rotateDegree);
        rotateTransform.appendTranslation(-pivotX, -pivotY);
        g2d.setTransform(rotateTransform);


        g2d.fillRect(platstart + firstWidth - stickwidth - 2, Images.BACKGROUND_HEIGHT - platheight - engine.getStickLength(),
                stickwidth, engine.getStickLength());
        g2d.restore();

        Platforms(g2d);
        Hero(g2d);
        Cherry(g2d);

        getChildren().clear();
        Score(g2d);
        Button butpause = new Button();
        ImageView imgpause = new ImageView(Images.pause);
        imgpause.setFitWidth(50); // Set the preferred width of the image
        imgpause.setFitHeight(50); // Set the preferred height of the image
        imgpause.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butpause.setStyle("-fx-background-color: transparent;");
        butpause.setGraphic(imgpause);
        butpause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                controller.pause(mouseEvent);
            }
        });
        butpause.setPrefSize(50, 50);
        butpause.setLayoutX( 0 );
        butpause.setLayoutY(0);
        getChildren().add(butpause);
        getChildren().add(canvas);
    }

    private void Platforms(GraphicsContext g2d) {
        g2d.setFill(Color.BLACK);
        g2d.translate(-moveValue, 0);
        g2d.fillRect(platstart, Images.BACKGROUND_HEIGHT - platheight, firstWidth, platheight);

        if (!engine.isMoving() && rotateDegree == 0 && secondplatpos != platstart + firstWidth + engine.getDistance()) {
            secondplatpos -= 20;
        }
        if (secondplatpos < platstart + firstWidth + engine.getDistance()) {
            secondplatpos = platstart + firstWidth + engine.getDistance();
        }

        g2d.fillRect(secondplatpos,
                Images.BACKGROUND_HEIGHT - platheight, secondWidth, platheight);
    }

    private void Hero(GraphicsContext g2d) {
        if (x <= platstart + firstWidth) {
            controller.upsideDown = false;
        }


        if (engine.isMoving() && controller.isUpsideDown()) {
            g2d.translate(0, Images.BACKGROUND_HEIGHT + 160);
            g2d.scale(1, -1);
        }

        if (rotateDegree == 90 && x < dest) {
            g2d.drawImage(heroImage, x, y - Images.HeroHeight);
            count++;
            count %= 8;
            if (count % 8 == 0) {
                i++;
                i %= 4;
            }
        } else {
            g2d.drawImage(heroImage, x, y - Images.HeroHeight);
        }
    }

    private void Cherry(GraphicsContext g2d) {
        if (!ischerryeaten) {
            g2d.drawImage(Images.cherry, platstart + firstWidth + engine.getcherryPos(),
                    Images.BACKGROUND_HEIGHT - platheight + 5);
        }
    }

    private void CherryEaten() {
        if (controller.isUpsideDown() && x + Images.HeroWidth >= platstart + firstWidth + engine.getcherryPos()
                && x <= platstart + firstWidth + engine.getcherryPos() + 25) {
            ischerryeaten = true;
        }
    }

    private void GameOver() {
        if (controller.isUpsideDown() && x + Images.HeroWidth >= platstart + firstWidth + engine.getDistance()) {
                engine.setGameOver(true);
                dest = x;
        }
    }

    private void Score(GraphicsContext g2d) {
        g2d.setFont(Font.font("", 30));
        g2d.setFill(Color.BLACK);
        g2d.fillText(""+engine.getScore(), 250, 50);
        g2d.drawImage(Images.cherry, 435, 70);
        g2d.fillText("" + engine.getcherryNum(), 400, 90);
    }

    private void createPauseButton() {
        Button butpause = new Button();
        ImageView imgpause = new ImageView(Images.pause);
        imgpause.setFitWidth(50);
        imgpause.setFitHeight(50);
        imgpause.setPreserveRatio(true);
        butpause.setStyle("-fx-background-color: transparent;");
        butpause.setGraphic(imgpause);
        butpause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.pause(mouseEvent);
            }
        });
        butpause.setPrefSize(50, 50);
        butpause.setLayoutX(0);
        butpause.setLayoutY(0);
        getChildren().add(butpause);
    }

}
