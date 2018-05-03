package view;

import control.Controller;
import model.Message;

public class GUI implements IObserver {

    Controller controller;

    @Override
    public void update(Message msg) {
        // Hier GUI updaten
    }
}
