package model;

import java.util.ArrayList;

public class Spieler {

    private ArrayList<Karte> karten;
    private float budget;
    /** Derzeitiger Einsatz */
    private float einsatz;

    public Spieler(float budget) {
        this.budget = budget;
        karten = new ArrayList<>();
    }

    public int getKartenSumme() {
        int summe = 0;
        for(Karte k : karten) {
            summe += k.getWert();
        }
        // Wenn über dem Limit
        if(summe > 21) {
            summe = 0;
            for (Karte k : karten) {
                // Alternative Werte verwenden
                // (Ass hat statt 11 den Wert 1)
                summe += k.getAltWert();
            }
        }
        return summe;
    }

    public void erhalteKarte(Karte k) {
        this.karten.add(k);
    }

    public void resetKarten() {
        this.karten.clear();
    }

    public ArrayList<Karte> getKarten() {
        return karten;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        if(budget < 0) budget = 0; // TODO exception oder so?
        this.budget = budget;
    }

    public float getEinsatz() {
        return einsatz;
    }

    public void setEinsatz(float einsatz) {
        if(einsatz > budget) {
            this.einsatz = budget;
            budget = 0;
        } else {
            this.einsatz = einsatz;
            setBudget(getBudget()-this.einsatz);
        }
    }
}