package control;

import model.Game;
import model.Model;
import model.command.AddSpielerCmd;
import model.command.CommandRecorder;
import model.command.ICommand;
import model.command.KarteGebenCmd;
import model.strategy.NeueKarteBis17;
import model.Spieler;

public class Controller {

    private CommandRecorder recorder;
    private Model model;

    /** Zu Beginn erhalten alle Spieler ein paar Karten */
    public void spielerKartenAusteilenBeginn() {
        for(Spieler s : model.getSpieler()) {
            for(int i = 0; i < Game.ANZ_KARTEN_BEGINN; i++) {
                gebeKarte(s);
            }
        }
    }

    public void gebeKarte(Spieler s) {
        ICommand c = new KarteGebenCmd(model, s);
        recorder.doCommand(c);
    }

    public void addSpieler(Spieler s) {
        ICommand c = new AddSpielerCmd(model, s);
        recorder.doCommand(c);
    }
    
    public void initGame() {

        recorder = new CommandRecorder();
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


    /** Von GUI Befehlen
     */
    public void undo() {
        recorder.undoCommand();
    }

    /** Von GUI Befehlen
     */
    public void redo() {
        recorder.redoCommand();
    }
}
