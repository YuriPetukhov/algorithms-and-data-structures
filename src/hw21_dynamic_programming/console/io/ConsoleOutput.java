package hw21_dynamic_programming.console.io;

public interface ConsoleOutput {

    void print(String text);

    void println(String text);

    default void println() {
        println("");
    }
}
