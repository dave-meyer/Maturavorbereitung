package model;

public class Karte {

    private Kartenwert wert;
    private Kartenfarbe farbe;

    public Karte(Kartenwert wert, Kartenfarbe farbe) {
        this.wert = wert;
        this.farbe = farbe;
    }

    public int getWert() {
        return wert.getWert();
    }

    public int getAltWert() {
        return wert.getAltWert();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Karte) {
            Karte k = (Karte) obj;
            return (this.farbe.equals(k.farbe) && this.wert.equals(k.wert));
            // Verständlichere Schreibweise:
            //if(this.farbe.equals(k.farbe) && this.wert.equals(k.wert)) return true;
        }
        return false;
    }
}
