package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class IntegerField implements InputField<Integer> {

    private final InputKey<Integer> key;
    private final String prompt;

    public IntegerField(InputKey<Integer> key, String prompt) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.prompt = Objects.requireNonNull(prompt, "Prompt must not be null.");
    }

    @Override
    public InputKey<Integer> key() {
        return key;
    }

    @Override
    public Integer read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        while (true) {
            output.print(prompt);
            String value = input.readLine().trim();
            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException exception) {
                output.println(
                        "Ошибка ввода: ожидалось целое число, получено: " + value
                );
            }
        }
    }
}
