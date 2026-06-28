package hw21_dynamic_programming.console;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputValues;
import hw21_dynamic_programming.console.model.field.InputField;

import java.util.Objects;

public final class InputFormReader {

    private final ConsoleInput input;
    private final ConsoleOutput output;

    public InputFormReader(
            ConsoleInput input,
            ConsoleOutput output
    ) {
        this.input = Objects.requireNonNull(input, "Console input must not be null.");
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
    }

    public <I> I read(InputForm<I> form) {
        Objects.requireNonNull(form, "Input form must not be null.");
        showInstructions(form);

        InputValues values = new InputValues();
        for (InputField<?> field : form.fields()) {
            readField(field, values);
        }
        return form.assemble(values);
    }

    private void showInstructions(InputForm<?> form) {
        if (form.instructions().isEmpty()) {
            return;
        }
        for (String line : form.instructions()) {
            output.println(line);
        }
        output.println();
    }

    private <T> void readField(
            InputField<T> field,
            InputValues values
    ) {
        T value = field.read(input, output, values);
        values.put(field.key(), value);
    }
}
