package model.command;

import model.Model;
import model.Spieler;

public class AddSpielerCmd implements ICommand {

    private Model model;
    private Spieler spieler;

    public AddSpielerCmd(Model model, Spieler spieler) {
        this.model = model;
        this.spieler = spieler;
    }

    @Override
    public void doCommand() {
        getModel().addSpieler(spieler);
    }

    @Override
    public void undoCommand() {
        getModel().removeSpieler(spieler);
    }

    public Model getModel() {
        return model;
    }

    public Spieler getSpieler() {
        return spieler;
    }
}