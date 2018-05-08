package model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spieler {

    ArrayList<Point> felder;
    java.awt.Color color;

    public Spieler(Color color) {
        felder = new ArrayList<>();
        this.color = color;
    }

    public ArrayList<Point> getFelder() {
        return felder;
    }

    public void setFelder(ArrayList<Point> felder) {
        this.felder = felder;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
