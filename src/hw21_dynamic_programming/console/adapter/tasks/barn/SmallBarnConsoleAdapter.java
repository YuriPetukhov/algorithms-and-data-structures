package hw21_dynamic_programming.console.adapter.tasks.barn;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.DenseBlockedMatrixField;
import hw21_dynamic_programming.tasks.smallbarn.SmallBarnTaskModule;

import java.util.List;

public final class SmallBarnConsoleAdapter
        implements ConsoleTaskAdapter<boolean[][], Integer> {

    private static final InputKey<boolean[][]> MATRIX =
            new InputKey<>("small-barn-matrix", boolean[][].class);

    private final InputForm<boolean[][]> inputForm = InputForm.describedBy(
            List.of(
                    "Введите размеры N M одной строкой: N — число столбцов, M — число строк.",
                    "Для маленького сарая N и M должны быть от 1 до 30.",
                    "Затем введите M строк по N значений: 0 — свободная клетка, 1 — занятая.",
                    "Пример размеров: 4 3. Пример строки матрицы: 0 0 1 0."
            ),
            List.of(
                    new DenseBlockedMatrixField(
                            MATRIX,
                            "Размеры N M: ",
                            "Строка %d (%d значений): ",
                            30,
                            30
                    )
            ),
            values -> values.get(MATRIX)
    );

    @Override
    public String taskId() {
        return SmallBarnTaskModule.TASK_ID;
    }

    @Override
    public Class<boolean[][]> inputType() {
        return boolean[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public InputForm<boolean[][]> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<Integer> resultView() {
        return ResultView.of("Результат: ", String::valueOf);
    }
}
