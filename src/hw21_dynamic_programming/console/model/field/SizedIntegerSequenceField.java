package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class SizedIntegerSequenceField implements InputField<int[]> {

    private final InputKey<int[]> key;
    private final String sizePrompt;
    private final String valuePromptTemplate;
    private final int maximumSize;
    private final int minimumValue;
    private final int maximumValue;

    public SizedIntegerSequenceField(
            InputKey<int[]> key,
            String sizePrompt,
            String valuePromptTemplate,
            int maximumSize,
            int minimumValue,
            int maximumValue
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.sizePrompt = Objects.requireNonNull(sizePrompt, "Size prompt must not be null.");
        this.valuePromptTemplate = Objects.requireNonNull(
                valuePromptTemplate,
                "Value prompt template must not be null."
        );
        this.maximumSize = maximumSize;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
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
        int size = new RangedIntegerField(
                new InputKey<>("sequence-size", Integer.class),
                sizePrompt,
                1,
                maximumSize,
                "Размер N"
        ).read(input, output, previousValues);

        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = new RangedIntegerField(
                    new InputKey<>("sequence-value-" + index, Integer.class),
                    valuePromptTemplate.formatted(index),
                    minimumValue,
                    maximumValue,
                    "A[" + index + "]"
            ).read(input, output, previousValues);
        }
        return values;
    }
}
