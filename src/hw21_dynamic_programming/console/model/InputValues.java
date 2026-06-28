package hw21_dynamic_programming.console.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class InputValues {

    private final Map<InputKey<?>, Object> values = new LinkedHashMap<>();

    public <T> void put(InputKey<T> key, T value) {
        Objects.requireNonNull(key, "Input key must not be null.");
        if (value != null && !key.type().isInstance(value)) {
            throw new IllegalArgumentException(
                    "Value for key '%s' must be %s."
                            .formatted(key.name(), key.type().getName())
            );
        }
        if (values.containsKey(key)) {
            throw new IllegalStateException(
                    "Input value is already present: " + key.name()
            );
        }
        values.put(key, value);
    }

    public <T> T get(InputKey<T> key) {
        Objects.requireNonNull(key, "Input key must not be null.");
        if (!values.containsKey(key)) {
            throw new IllegalStateException(
                    "Input value is missing: " + key.name()
            );
        }
        return key.type().cast(values.get(key));
    }
}
