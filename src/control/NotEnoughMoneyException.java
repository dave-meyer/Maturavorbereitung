package control;

public class NotEnoughMoneyException extends Exception {

    private String message;

    public NotEnoughMoneyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}