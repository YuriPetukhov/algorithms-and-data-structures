package hw21_dynamic_programming.console.presentation;

import hw21_dynamic_programming.console.io.ConsoleOutput;

import java.util.Objects;

public final class StandardResultPresenter
        implements ResultPresenter {

    private final ConsoleOutput output;

    public StandardResultPresenter(ConsoleOutput output) {
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
    }

    @Override
    public void present(
            String label,
            String formattedResult
    ) {
        output.println(label + formattedResult);
    }
}
