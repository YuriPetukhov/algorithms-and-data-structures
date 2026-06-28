package hw21_dynamic_programming.console.io;

public final class EndOfInputException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EndOfInputException() {
        super("Console input has ended.");
    }
}
