package model;

public class Feld {

    Spieler s;
    Point p;

    public Feld(Point p, Spieler s) {
        this.s = s;
        this.p = p;
    }

    public Spieler getS() {
        return s;
    }

    public void setS(Spieler s) {
        this.s = s;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }
}
