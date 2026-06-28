package hw21_dynamic_programming.console.model;

import java.util.Objects;

public record InputKey<T>(String name, Class<T> type) {

    public InputKey {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Input key name must not be blank.");
        }
        Objects.requireNonNull(type, "Input key type must not be null.");
    }
}
