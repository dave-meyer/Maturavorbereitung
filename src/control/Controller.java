package control;

import model.Game;
import model.Model;
import model.strategy.NeueKarteBis17;
import model.Spieler;

public class Controller {

    private Model model;

    /** Zu Beginn erhalten alle Spieler ein paar Karten */
    public void spielerKartenAusteilenBeginn() {
        for(Spieler s : model.getSpieler()) {
            for(int i = 0; i < Game.ANZ_KARTEN_BEGINN; i++) {
                model.gebeKarte(s);
            }
        }
    }

    public void initGame() {

        model = new Model();
        model.getGame().initGame();

        // Spieler hinzufuegen sollte von GUI Commands kommen
        model.addSpieler(new Spieler(1000));
        model.addSpieler(new Spieler(1000));
        model.addSpieler(new Spieler(1000));
        model.getCroupier().setStrategy(new NeueKarteBis17()); // hier könnte Ihre Strategy stehen

        // Jedem Spieler seine Anfangs-Karten geben
        spielerKartenAusteilenBeginn();
    }
}
