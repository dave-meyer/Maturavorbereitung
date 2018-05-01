package model;

public enum Kartenwert {

    ASS(11, 1),
    ZWEI(2),
    DREI(3),
    VIER(4),
    FUENF(5),
    SECHS(6),
    SIEBEN(7),
    ACHT(8),
    NEUN(9),
    BUBE(10),
    DAME(10),
    KOENIG(10);

    private int wert;
    private int altWert;
    Kartenwert(int wert) {
        this.wert = wert;
        this.altWert = this.wert;
    }
    Kartenwert(int wert, int altWert) {
        this(wert);
        this.altWert = altWert;
    }
    public int getWert() {
        return wert;
    }
    public int getAltWert() {  return altWert;  }
}
