package model.strategy;

public class NeueKarteBis15 implements ICroupierStrategy {

    @Override
    public boolean neueKarte(int kartenSumme) {
        return kartenSumme < 15;
        // oder:
        // if(kartenSumme < 15) return true;
        // else return false;
    }
}
