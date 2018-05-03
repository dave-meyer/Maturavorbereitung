package model.command;

import model.Model;
import model.Spieler;

public class KarteGebenCmd implements ICommand {

    private Model model;
    private Spieler spieler;

    public KarteGebenCmd(Model model, Spieler spieler) {
        this.model = model;
        this.spieler = spieler;
    }

    public Model getModel() {
        return model;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    @Override
    public void doCommand() {
        getModel().gebeKarte(getSpieler());
    }

    @Override
    public void undoCommand() {
        getModel().entferneKarte(getSpieler());
    }
}
