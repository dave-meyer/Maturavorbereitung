package model;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    ArrayList<Spieler> spieler;
    Feld[][] points = new Feld[10][10];

    public Game() {
        spieler = new ArrayList<>();
    }

    @Override
    public String toString() {
        char[][] zeichen = new char[10][10];
        for(int i = 0; i < zeichen.length; i++) {
            for(int j = 0; j < zeichen[i].length; j++) {
                zeichen[i][j] = '0';
            }
        }
        for(Spieler s : spieler) {
            for(Point p : s.getFelder()) {
                zeichen[p.y][p.x] = 'X';
            }
        }
        return ""; // ausgabe
    }

    public static void main(String[] args) {
        new Game().init();

    }

    public void init() {
        spieler.add(new Spieler(Color.RED));
        points[5][4] = new Feld(new Point(5, 4), spieler.get(0));
        points[4][4] = new Feld(new Point(4, 4), spieler.get(0));
        points[3][4] = new Feld(new Point(3, 4), spieler.get(0));
        points[2][4] = new Feld(new Point(2, 4), spieler.get(0));
        System.out.println(getWinner());
    }

    Spieler getWinner() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(points[i][j] != null) {
                    System.out.println("-" + i + " " + j);
                    //Spieler s = points[i][j].getS();
                    if(passtDesFeld(points[i][j])) {
                        return points[i][j].getS();
                    }
                }
            }
        }
        return null;
    }

    boolean passtDesFeld(Feld f) {
        for(int a = -1; a < 2; a++) {
            for(int b = -1; b < 2; b++) {
                if(a == 0 && b == 0) continue;
                int zaehler = 1;
                // Abgrenzung der Felder
                while(true) {
                    System.out.println("Feld: " + (f.getP().x+a*zaehler) + " " + (f.getP().y+b*zaehler));
                    System.out.println("Zaehler: " + zaehler*a + " " + zaehler*b);
                    Feld f2 = points[f.getP().x+(a*zaehler)][f.getP().y+(b*zaehler)];
                    if(f2 != null && f2.getS().equals(f.getS())) {
                        zaehler++;
                        if(zaehler>3) return true;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
