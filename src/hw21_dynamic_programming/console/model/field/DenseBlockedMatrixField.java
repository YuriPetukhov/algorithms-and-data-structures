package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;

import java.util.Objects;

public final class DenseBlockedMatrixField implements InputField<boolean[][]> {

    private final InputKey<boolean[][]> key;
    private final String sizePrompt;
    private final String rowPromptTemplate;
    private final int maximumWidth;
    private final int maximumHeight;

    public DenseBlockedMatrixField(
            InputKey<boolean[][]> key,
            String sizePrompt,
            String rowPromptTemplate,
            int maximumWidth,
            int maximumHeight
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.sizePrompt = Objects.requireNonNull(sizePrompt, "Size prompt must not be null.");
        this.rowPromptTemplate = Objects.requireNonNull(
                rowPromptTemplate,
                "Row prompt template must not be null."
        );
        this.maximumWidth = maximumWidth;
        this.maximumHeight = maximumHeight;
    }

    @Override
    public InputKey<boolean[][]> key() {
        return key;
    }

    @Override
    public boolean[][] read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        int[] size = readSize(input, output);
        int width = size[0];
        int height = size[1];
        boolean[][] blocked = new boolean[height][width];

        for (int row = 0; row < height; row++) {
            int[] values = readRow(input, output, row + 1, width);
            for (int column = 0; column < width; column++) {
                blocked[row][column] = values[column] == 1;
            }
        }
        return blocked;
    }

    private int[] readSize(ConsoleInput input, ConsoleOutput output) {
        while (true) {
            output.print(sizePrompt);
            try {
                int[] size = InputParsing.parseExactIntegers(input.readLine(), 2);
                InputParsing.requireRange(size[0], 1, maximumWidth, "N");
                InputParsing.requireRange(size[1], 1, maximumHeight, "M");
                return size;
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }

    private int[] readRow(
            ConsoleInput input,
            ConsoleOutput output,
            int rowNumber,
            int width
    ) {
        while (true) {
            output.print(rowPromptTemplate.formatted(rowNumber, width));
            try {
                int[] values = InputParsing.parseExactIntegers(input.readLine(), width);
                InputParsing.requireBinary(values);
                return values;
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }
}
