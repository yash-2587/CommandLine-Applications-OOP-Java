package com.example.stickhero;

import java.util.Random;

public class Platform {
    public static final int MIN_SIZE = 50;
    public static final int MAX_SIZE = 120;
    private int width;

    public Platform() {
        Random rand = new Random();

        width = 0;
        while (width < Platform.MIN_SIZE)
            width = rand.nextInt(Platform.MAX_SIZE);
    }

    public int getWidth() {
        return width;
    }
}
