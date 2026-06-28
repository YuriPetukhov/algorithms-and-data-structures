package hw21_dynamic_programming.console.presentation;

import hw21_dynamic_programming.console.io.ConsoleOutput;

import java.util.Objects;

public final class StandardErrorPresenter
        implements ErrorPresenter {

    private final ConsoleOutput output;

    public StandardErrorPresenter(ConsoleOutput output) {
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
    }

    @Override
    public void present(RuntimeException exception) {
        Objects.requireNonNull(exception, "Exception must not be null.");
        String message = exception.getMessage();
        output.println(
                "Ошибка выполнения: "
                        + (message == null ? exception.getClass().getSimpleName() : message)
        );
    }
}
