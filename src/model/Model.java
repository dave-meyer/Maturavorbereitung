package model;

import model.command.CommandRecorder;
import model.command.ICommand;
import model.command.KarteGebenCmd;

import java.util.ArrayList;

public class Model extends Observeable {

    private Game game;
    private CommandRecorder recorder;

    public Model() {
        game = new Game();
        recorder = new CommandRecorder();
    }

    public void gebeKarte(Spieler s) {
        //game.gebeKarte(s); Methode wird im CommandRecoder ausgeführt
        ICommand c = new KarteGebenCmd(this, s);
        recorder.doCommand(c);
        notifyObservers(new Message()); // Message sollte irgendwas beinhalten
    }


    public void entferneKarte(Spieler s) {
        game.entferneKarte(s);
        notifyObservers(new Message()); // Message sollte irgendwas beinhalten
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Spieler> getSpieler() {
        return game.getSpieler();
    }

    public void addSpieler(Spieler spieler) {
        game.addSpieler(spieler);
        notifyObservers(new Message()); // Message sollte irgendwas beinhalten
    }

    public void removeSpieler(Spieler spieler) {
        game.removeSpieler(spieler);
        notifyObservers(new Message()); // Message sollte irgendwas beinhalten
    }

    public Croupier getCroupier() {
        return game.getCroupier();
    }
}