package view;

import model.Message;

public interface IObserver {

    void update(Message msg);
}
