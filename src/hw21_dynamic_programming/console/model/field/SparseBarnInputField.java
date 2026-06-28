package hw21_dynamic_programming.console.model.field;

import hw21_dynamic_programming.console.io.ConsoleInput;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.InputValues;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.barn.model.Cell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class SparseBarnInputField implements InputField<BarnInput> {

    private final InputKey<BarnInput> key;
    private final String sizePrompt;
    private final String countPrompt;
    private final String coordinatePromptTemplate;

    public SparseBarnInputField(
            InputKey<BarnInput> key,
            String sizePrompt,
            String countPrompt,
            String coordinatePromptTemplate
    ) {
        this.key = Objects.requireNonNull(key, "Input key must not be null.");
        this.sizePrompt = Objects.requireNonNull(sizePrompt, "Size prompt must not be null.");
        this.countPrompt = Objects.requireNonNull(countPrompt, "Count prompt must not be null.");
        this.coordinatePromptTemplate = Objects.requireNonNull(
                coordinatePromptTemplate,
                "Coordinate prompt template must not be null."
        );
    }

    @Override
    public InputKey<BarnInput> key() {
        return key;
    }

    @Override
    public BarnInput read(
            ConsoleInput input,
            ConsoleOutput output,
            InputValues previousValues
    ) {
        int[] size = readSize(input, output);
        int width = size[0];
        int height = size[1];
        int count = readCount(input, output);
        List<Cell> cells = readCells(input, output, width, height, count);
        return new BarnInput(width, height, cells);
    }

    private int[] readSize(ConsoleInput input, ConsoleOutput output) {
        while (true) {
            output.print(sizePrompt);
            try {
                int[] values = InputParsing.parseExactIntegers(input.readLine(), 2);
                InputParsing.requireRange(values[0], 1, 1000, "N");
                InputParsing.requireRange(values[1], 1, 1000, "M");
                return values;
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }

    private int readCount(ConsoleInput input, ConsoleOutput output) {
        return new RangedIntegerField(
                new InputKey<>("building-count", Integer.class),
                countPrompt,
                0,
                10_000,
                "Количество построек T"
        ).read(input, output, new InputValues());
    }

    private List<Cell> readCells(
            ConsoleInput input,
            ConsoleOutput output,
            int width,
            int height,
            int count
    ) {
        List<Cell> cells = new ArrayList<>(count);
        Set<Long> used = new HashSet<>();

        for (int index = 0; index < count; index++) {
            cells.add(readCell(input, output, width, height, index + 1, used));
        }
        return cells;
    }

    private Cell readCell(
            ConsoleInput input,
            ConsoleOutput output,
            int width,
            int height,
            int number,
            Set<Long> used
    ) {
        while (true) {
            output.print(coordinatePromptTemplate.formatted(number));
            try {
                int[] values = InputParsing.parseExactIntegers(input.readLine(), 2);
                InputParsing.requireRange(values[0], 0, width - 1, "X");
                InputParsing.requireRange(values[1], 0, height - 1, "Y");
                long coordinateKey = ((long) values[1] << 32)
                        ^ (values[0] & 0xffffffffL);
                if (!used.add(coordinateKey)) {
                    throw new IllegalArgumentException(
                            "координата (%d, %d) уже была указана."
                                    .formatted(values[0], values[1])
                    );
                }
                return new Cell(values[0], values[1]);
            } catch (IllegalArgumentException exception) {
                output.println("Ошибка ввода: " + exception.getMessage());
            }
        }
    }
}
