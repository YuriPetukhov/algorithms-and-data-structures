package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class IntegerListField implements InputField<int[]> {

    private final InputKey<int[]> key;
    private final String prompt;
    private final int expectedSize;

    public IntegerListField(
            InputKey<int[]> key,
            String prompt,
            int expectedSize
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.prompt = Objects.requireNonNull(prompt, "Prompt must not be null.");
        if (expectedSize <= 0) {
            throw new IllegalArgumentException("Expected size must be positive.");
        }
        this.expectedSize = expectedSize;
    }

    @Override
    public InputKey<int[]> key() {
        return key;
    }

    @Override
    public int[] read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        while (true) {
            output.print(prompt);
            String line = input.readLine().trim();
            try {
                return parse(line);
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }

    private int[] parse(String line) {
        if (line.isEmpty()) {
            throw new IllegalArgumentException("строка не должна быть пустой.");
        }

        String[] tokens = line.split("\\s+");
        if (tokens.length != expectedSize) {
            throw new IllegalArgumentException(
                    "ожидалось %d чисел, получено %d."
                            .formatted(expectedSize, tokens.length)
            );
        }

        int[] values = new int[expectedSize];
        for (int index = 0; index < expectedSize; index++) {
            try {
                values[index] = Integer.parseInt(tokens[index]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException(
                        "'%s' не является целым числом."
                                .formatted(tokens[index]),
                        exception
                );
            }
        }
        return values;
    }
}
