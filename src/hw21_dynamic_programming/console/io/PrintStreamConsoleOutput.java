package hw21_dynamic_programming.console.io;

import java.io.PrintStream;
import java.util.Objects;

public final class PrintStreamConsoleOutput implements ConsoleOutput {

    private final PrintStream output;

    public PrintStreamConsoleOutput(PrintStream output) {
        this.output = Objects.requireNonNull(
                output,
                "Output stream must not be null."
        );
    }

    @Override
    public void print(String text) {
        output.print(text);
    }

    @Override
    public void println(String text) {
        output.println(text);
    }
}
