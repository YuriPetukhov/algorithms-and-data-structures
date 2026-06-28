package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class RangedIntegerField implements InputField<Integer> {

    private final InputKey<Integer> key;
    private final String prompt;
    private final int minimum;
    private final int maximum;
    private final String valueName;

    public RangedIntegerField(
            InputKey<Integer> key,
            String prompt,
            int minimum,
            int maximum,
            String valueName
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.prompt = Objects.requireNonNull(prompt, "Prompt must not be null.");
        this.valueName = Objects.requireNonNull(valueName, "Value name must not be null.");
        if (minimum > maximum) {
            throw new IllegalArgumentException("Minimum must not exceed maximum.");
        }
        this.minimum = minimum;
        this.maximum = maximum;
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
            String line = input.readLine().trim();
            try {
                int value = Integer.parseInt(line);
                InputParsing.requireRange(value, minimum, maximum, valueName);
                return value;
            } catch (NumberFormatException exception) {
                output.println("Ошибка ввода: ожидалось целое число.");
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }
}
