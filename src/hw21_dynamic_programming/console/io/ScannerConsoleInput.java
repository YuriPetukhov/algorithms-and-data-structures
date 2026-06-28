package hw21_dynamic_programming.console.io;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public final class ScannerConsoleInput implements ConsoleInput {

    private final Scanner scanner;

    public ScannerConsoleInput(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream must not be null.");
        this.scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    }

    @Override
    public String readLine() {
        if (!scanner.hasNextLine()) {
            throw new EndOfInputException();
        }
        return scanner.nextLine();
    }
}
