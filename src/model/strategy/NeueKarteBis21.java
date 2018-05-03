package model.strategy;

public class NeueKarteBis21 implements ICroupierStrategy {

    @Override
    public boolean neueKarte(int kartenSumme) {
        return kartenSumme < 21;
        // oder:
        // if(kartenSumme < 21) return true;
        // else return false;
    }
}
