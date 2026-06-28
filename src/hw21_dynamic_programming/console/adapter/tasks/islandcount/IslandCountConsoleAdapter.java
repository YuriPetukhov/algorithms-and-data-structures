package hw21_dynamic_programming.console.adapter.tasks.islandcount;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.SquareBinaryMatrixField;
import hw21_dynamic_programming.tasks.islandcount.IslandCountTaskModule;

import java.util.List;

public final class IslandCountConsoleAdapter
        implements ConsoleTaskAdapter<int[][], Integer> {

    private static final InputKey<int[][]> MATRIX =
            new InputKey<>("binary-matrix", int[][].class);

    private final InputForm<int[][]> inputForm = InputForm.describedBy(
            List.of(
                    "Введите размер квадратной матрицы N от 1 до 100.",
                    "Затем введите N строк по N значений 0 или 1 через пробел.",
                    "Пример для N=3: строки '1 1 0', '0 1 0', '1 0 1'."
            ),
            List.of(
                    new SquareBinaryMatrixField(
                            MATRIX,
                            "Размер N: ",
                            "Строка %d (%d значений): "
                    )
            ),
            values -> values.get(MATRIX)
    );

    @Override
    public String taskId() {
        return IslandCountTaskModule.TASK_ID;
    }

    @Override
    public Class<int[][]> inputType() {
        return int[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public InputForm<int[][]> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<Integer> resultView() {
        return ResultView.of("Результат: ", String::valueOf);
    }
}
