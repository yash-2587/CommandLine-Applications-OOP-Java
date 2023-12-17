package com.example.stickhero;

import java.util.Random;

public class GameEngine {
    public static final int MIN_DISTANCE = 100;
    public static final int MAX_DISTANCE = 250;

    private boolean gameOver;
    private boolean moving;
    private Platform first, second;
    private int distance;
    private int stickLength;
    private int score;

    private int cherryNum;
    private int cherryPos;


    public void init() {
        first = new Platform();
        second = new Platform();

        assignDistance();
        stickLength = 0;
        score = 0;
        cherryNum = 0;

        gameOver = false;
        moving = false;
    }

    private void assignDistance() {
        Random rand = new Random();

        distance = 0;
        while (distance < GameEngine.MIN_DISTANCE)
            distance = rand.nextInt(GameEngine.MAX_DISTANCE);

        cherryPos = 0;
        while (cherryPos < 50) {
            cherryPos = rand.nextInt(distance - 50);
        }
    }

    public void increaseStickLength() {
        stickLength+= 3;
    }

    public void checkForGameOver() {
        if (stickLength < distance + 5 || distance + second.getWidth() + 5 < stickLength)
            gameOver = true;
    }

    public void nextPlat() {
        first = second;
        second = new Platform();
        assignDistance();

        moving = false;
        stickLength = 0;
        score++;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Platform getFirstRect() {
        return first;
    }

    public Platform getSecondRect() {
        return second;
    }

    public int getDistance() {
        return distance;
    }

    public int getStickLength() {
        return stickLength;
    }


    public int getScore() {
        return score;
    }

    public int getcherryPos() {
        return cherryPos;
    }

    public void cherryEaten() {
        cherryNum++;
    }

    public void decrementCherry(){
        cherryNum -= 3;
    }

    public int getcherryNum() {
        return cherryNum;
    }
}

