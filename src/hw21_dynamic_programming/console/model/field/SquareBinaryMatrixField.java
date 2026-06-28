package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class SquareBinaryMatrixField implements InputField<int[][]> {

    private final InputKey<int[][]> key;
    private final String sizePrompt;
    private final String rowPromptTemplate;

    public SquareBinaryMatrixField(
            InputKey<int[][]> key,
            String sizePrompt,
            String rowPromptTemplate
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.sizePrompt = Objects.requireNonNull(sizePrompt, "Size prompt must not be null.");
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
        int size = new RangedIntegerField(
                new InputKey<>("matrix-size", Integer.class),
                sizePrompt,
                1,
                100,
                "Размер N"
        ).read(input, output, previousValues);

        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            matrix[row] = readRow(input, output, row + 1, size);
        }
        return matrix;
    }

    private int[] readRow(
            ConsoleInput input,
            ConsoleOutput output,
            int rowNumber,
            int size
    ) {
        while (true) {
            output.print(rowPromptTemplate.formatted(rowNumber, size));
            try {
                int[] values = InputParsing.parseExactIntegers(input.readLine(), size);
                InputParsing.requireBinary(values);
                return values;
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }
}
