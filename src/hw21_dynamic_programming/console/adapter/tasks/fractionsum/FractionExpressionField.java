package hw21_dynamic_programming.console.adapter.tasks.fractionsum;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;
import hw21_dynamic_programming.console.model.field.InputField;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionInput;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class FractionExpressionField implements InputField<FractionInput> {

    private static final Pattern EXPRESSION = Pattern.compile(
            "\\s*(\\d+)/(\\d+)\\+(\\d+)/(\\d+)\\s*"
    );

    private final InputKey<FractionInput> key;
    private final String prompt;

    FractionExpressionField(
            InputKey<FractionInput> key,
            String prompt
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.prompt = Objects.requireNonNull(prompt, "Prompt must not be null.");
    }

    @Override
    public InputKey<FractionInput> key() {
        return key;
    }

    @Override
    public FractionInput read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        while (true) {
            output.print(prompt);
            String line = input.readLine();
            Matcher matcher = EXPRESSION.matcher(line);
            if (!matcher.matches()) {
                output.println(
                        "Ошибка ввода: используйте формат a/b+c/d, например 1/2+1/3."
                );
                continue;
            }

            try {
                return new FractionInput(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                );
            } catch (NumberFormatException exception) {
                output.println(
                        "Ошибка ввода: каждое число должно помещаться в тип int."
                );
            }
        }
    }
}
