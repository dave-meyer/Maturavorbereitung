package model;

import java.util.ArrayList;

public abstract class Observeable {

    private ArrayList<IObserver> observer;

    public void notifyObservers(Message msg) {
        for(IObserver o : observer) {
            o.update(msg);
        }
    }

    public void addObserver(IObserver observer) {
        this.observer.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observer.remove(observer);
    }

    public ArrayList<IObserver> getObserver() {
        return observer;
    }
}