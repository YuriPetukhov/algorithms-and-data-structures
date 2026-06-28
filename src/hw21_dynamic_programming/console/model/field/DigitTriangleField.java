package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class DigitTriangleField implements InputField<int[][]> {

    private final InputKey<int[][]> key;
    private final String heightPrompt;
    private final String rowPromptTemplate;

    public DigitTriangleField(
            InputKey<int[][]> key,
            String heightPrompt,
            String rowPromptTemplate
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.heightPrompt = Objects.requireNonNull(heightPrompt, "Height prompt must not be null.");
        this.rowPromptTemplate = Objects.requireNonNull(
                rowPromptTemplate,
                "Row prompt template must not be null."
        );
    }

    @Override
    public InputKey<int[][]> key() {
        return key;
    }

    @Override
    public int[][] read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        int height = new RangedIntegerField(
                new InputKey<>("tree-height", Integer.class),
                heightPrompt,
                1,
                100,
                "Высота N"
        ).read(input, output, previousValues);

        int[][] tree = new int[height][];
        for (int row = 0; row < height; row++) {
            tree[row] = readRow(input, output, row + 1);
        }
        return tree;
    }

    private int[] readRow(
            ConsoleInput input,
            ConsoleOutput output,
            int size
    ) {
        while (true) {
            output.print(rowPromptTemplate.formatted(size, size));
            try {
                int[] values = InputParsing.parseExactIntegers(input.readLine(), size);
                for (int value : values) {
                    InputParsing.requireRange(value, 0, 9, "Каждая цифра");
                }
                return values;
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }
}
