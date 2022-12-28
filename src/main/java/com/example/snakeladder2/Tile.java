package com.example.snakeladder2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    // Constructor
    public Tile(int size) {

        setWidth(size);
        setHeight(size);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }
}