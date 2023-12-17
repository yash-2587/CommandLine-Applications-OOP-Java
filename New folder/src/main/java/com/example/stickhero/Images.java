package com.example.stickhero;

import javafx.scene.image.Image;

import java.io.File;

public class Images {
    public static Image background;
    public static Image startButton;
    public static Image hero;
    public static Image gamename;
    public static Image replay;
    public static Image cherry;
    public static Image icon;
    public static Image menu;
    public static Image home;
    public static Image savegame;
    public static Image back;
    public static Image pause;
    public static Image instructions;
    public static Image soundon;
    public static Image musicon;
    public static Image musicoff;
    public static Image soundoff;
    public static Image save;
    public static final int BACKGROUND_WIDTH = 500;
    public static final int BACKGROUND_HEIGHT = 600;
    public static final int HeroWidth = 25;
    public static final int HeroHeight = 25;

    static {
        try {
            File file = new File("images/background.png");
            background = new Image(file.toURI().toString());
            file = new File("images/btnresume.png");
            startButton = new Image(file.toURI().toString());
            file = new File("images/stand.png");
            hero = new Image(file.toURI().toString());
            file = new File("images/btnretry.png");
            replay = new Image(file.toURI().toString());
            file = new File("images/cherry.png");
            cherry = new Image(file.toURI().toString());
            file = new File("images/instruction.png");
            instructions = new Image(file.toURI().toString());
            file = new File("images/icon.jpg");
            icon = new Image(file.toURI().toString());
            file = new File("images/btnback.png");
            back = new Image(file.toURI().toString());
            file = new File("images/btnsound0.png");
            soundon = new Image(file.toURI().toString());
            file = new File("images/btnsound1.png");
            soundoff = new Image(file.toURI().toString());
            file = new File("images/btnmusic0.png");
            musicon = new Image(file.toURI().toString());
            file = new File("images/btnmusic1.png");
            musicoff = new Image(file.toURI().toString());
            file = new File("images/gamename.png");
            gamename = new Image(file.toURI().toString());
            file = new File("images/menu.png");
            menu = new Image(file.toURI().toString());
            file = new File("images/btnload.png");
            savegame = new Image(file.toURI().toString());
            file = new File("images/btnhome.png");
            home = new Image(file.toURI().toString());
            file = new File("images/pause.png");
            pause = new Image(file.toURI().toString());
            file = new File("images/save.png");
            save = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static String (String fileName) {
//        Path path = Paths.get("src", "main", "resources", fileName);
//        return path.toUri().toString();
//    }
}

