package control;

import model.Game;
import model.Model;
import model.Spieler;

public class Controller {

    private Model model;

    /** Zu Beginn erhalten alle Spieler ein paar Karten */
    public void spielerKartenAusteilenBeginn() {
        for(Spieler s : spieler) {
            for(int i = 0; i < Game.ANZ_KARTEN_BEGINN; i++) {
                gebeKarte(s);
            }
        }
    }

    public void initGame() {

        model = new Model();
        // TODO model und game

        // Jedem Spieler seine Anfangs-Karten geben
        spielerKartenAusteilenBeginn();
    }
}
