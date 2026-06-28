package hw21_dynamic_programming.console.model;

import java.util.Objects;

public record ResultView<O>(
        String label,
        ResultFormatter<O> formatter
) {

    public ResultView {
        Objects.requireNonNull(label, "Result label must not be null.");
        Objects.requireNonNull(formatter, "Result formatter must not be null.");
    }

    public static <O> ResultView<O> of(
            String label,
            ResultFormatter<O> formatter
    ) {
        return new ResultView<>(label, formatter);
    }
}
