package model;

import model.strategy.ICroupierStrategy;

public class Croupier extends Spieler {

    private ICroupierStrategy strategy;

    public Croupier(float budget) {
        super(budget);
    }

    public boolean neueKarte() {
        return strategy.neueKarte(getKartenSumme());
    }

    public void setStrategy(ICroupierStrategy strategy) {
        this.strategy = strategy;
    }

    public ICroupierStrategy getStrategy() {
        return strategy;
    }
}