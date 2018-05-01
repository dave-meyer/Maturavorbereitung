package model;

public class NeueKarteBis17 implements ICroupierStrategy {

    @Override
    public boolean neueKarte(int kartenSumme) {
        return kartenSumme < 17;
        // oder:
        // if(kartenSumme < 17) return true;
        // else return false;
    }
}
