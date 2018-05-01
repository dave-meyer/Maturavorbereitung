package model;

import java.util.ArrayList;

// Abstract nicht notwendig
public abstract class KarteFactory {

    public static final int ANZ_KARTEN_GESAMT = 48;

    public static ArrayList<Karte> getNeuenKartenstapel() {
        ArrayList<Karte> karten = new ArrayList<>();

        // Jede Wert-Farbe Kombination erzeugen
        // Für jeden Kartenwert im Kartenwert-Enum (Ass, 2, 3, ...)
        // alle Farben im Kartenfarbe-Enum (Pik, Karo, ...) wählen
        for(Kartenwert wert : Kartenwert.values()) {
            for(Kartenfarbe farbe : Kartenfarbe.values()) {
                karten.add(new Karte(wert, farbe));
            }
        }
        return karten;
    }
}