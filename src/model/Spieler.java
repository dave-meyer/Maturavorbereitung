package model;

import control.NotEnoughMoneyException;

import java.util.ArrayList;

public class Spieler {

    private ArrayList<Karte> karten;
    private float budget;
    /** Derzeitiger Einsatz */
    private float einsatz;

    public Spieler(float budget) {
        this.budget = budget; // oder vl setBudget? könnte aber Exception werfen
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

    /** Undo von erhalteKarte(Karte)
     */
    public Karte entferneKarte() {
        return this.karten.remove(karten.size()-1);
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

    public void setBudget(float budget) throws IllegalArgumentException {
        if(budget < 0) {
            //budget = 0;
            throw new IllegalArgumentException("Das Budget kann nicht weniger als 0 sein (Spieler "+this+")");
        }
        this.budget = budget;
    }

    public float getEinsatz() {
        return einsatz;
    }

    public void setEinsatz(float einsatz) throws NotEnoughMoneyException {
        if(einsatz > budget) {
            this.einsatz = budget;
            budget = 0;
            throw new NotEnoughMoneyException("Der Einsatz ist mehr als das verfügbare Budget. Der Einsatz beträgt nun " + this.einsatz + " und das Budget " + this.budget);
        } else {
            this.einsatz = einsatz;
            setBudget(getBudget()-this.einsatz);
        }
    }
}