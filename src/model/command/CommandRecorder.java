package model.command;

import java.util.Stack;

public class CommandRecorder {

    private Stack<ICommand> mainStack;
    private Stack<ICommand> secondaryStack;

    public CommandRecorder() {
        mainStack = new Stack<>();
        secondaryStack = new Stack<>();
    }

    public void doCommand(ICommand command) {
        // Command ausführen
        command.doCommand();
        // Command in MainStack einfügen
        mainStack.push(command);
    }

    public void undoCommand() {
        // Letzten ausgeführten Command holen und aus MainStack entfernen
        ICommand c = mainStack.pop();
        // Command rückgängig machen
        c.undoCommand();
        // Command in SecondaryStack einfügen
        secondaryStack.push(c);
    }

    public void redoCommand() {
        // Letzten rückgängig gemachten Command holen und aus SecondaryStack entfernen
        ICommand c = secondaryStack.pop();
        // Command ausführen
        c.doCommand();
        // Command in MainStack einfügen
        mainStack.push(c);
    }
}
