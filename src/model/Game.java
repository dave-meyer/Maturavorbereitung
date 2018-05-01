package model;


import java.util.ArrayList;
import java.util.Random;

public class Game {

    /** Anzahl der Karten die jeder Spieler zu Beginn erhält */
    public final static int ANZ_KARTEN_BEGINN = 2;

    private ArrayList<Karte> kartenstapel;
    private ArrayList<Spieler> spieler;
    //private Croupier croupier;

    public void initGame() {

        kartenstapel = KarteFactory.getNeuenKartenstapel();
        kartenstapel = kartenMischeln(kartenstapel);

        spieler = new ArrayList<>();
        spieler.add(new Croupier(0));
    }



    public ArrayList<Karte> kartenMischeln(ArrayList<Karte> k) {
        // Neues Array erstellen (enthält später die gemischelten Karten)
        ArrayList<Karte> temp = new ArrayList<>();
        // Zufallsgenerator erstellen
        Random random = new Random();
        int rand = 0;
        // Für jede Karte eine neue Position finden
        for(int i = 0; i < k.size(); i++) {
            // So lange Zufallszahlen wählen, bis eine gefunden wurde,
            // die noch nicht gewählt wurde
            // (sonst würden Karten überschrieben werden)
            while(true) {
                // Neue Zufallszahl erstellen
                rand = random.nextInt(k.size());
                // Ist diese Zufallszahl schon mal gewählt worden?
                // (ist an dieser Stelle im neuen Array schon eine Karte?)
                if(temp.get(rand) == null) { // Zahl wurde noch nicht gewählt
                    // Karte an dieser Stelle positionieren
                    temp.set(rand, k.get(i));
                    // While-Schleife beenden
                    break;
                }
                // Wenn die gewählte Zahl schon mal gewählt wurde -> while Schleife weiter ausführen
            }
        }
        // Array mit gemischtelten Karten zurückgeben
        return temp;
    }

    public void gebeKarte(Spieler spieler) {
        spieler.erhalteKarte(kartenstapel.remove(kartenstapel.size()-1));
    }


    public ArrayList<Karte> getKartenstapel() {
        return kartenstapel;
    }

    public void setKartenstapel(ArrayList<Karte> kartenstapel) {
        this.kartenstapel = kartenstapel;
    }

    public ArrayList<Spieler> getSpieler() {
        return spieler;
    }

    public void addSpieler(Spieler spieler) {
        this.spieler.add(spieler);
    }

    public void removeSpieler(Spieler spieler) {
        this.spieler.add(spieler);
    }

    public Croupier getCroupier() {
        for(Spieler s : spieler) {
            if(s instanceof Croupier) return (Croupier) s;
        }
        return null;
    }
}