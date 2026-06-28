package hw21_dynamic_programming.console.adapter.tasks.christmastree;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.DigitTriangleField;
import hw21_dynamic_programming.tasks.christmastree.ChristmasTreeTaskModule;

import java.util.List;

public final class ChristmasTreeConsoleAdapter
        implements ConsoleTaskAdapter<int[][], Integer> {

    private static final InputKey<int[][]> TREE =
            new InputKey<>("tree", int[][].class);

    private final InputForm<int[][]> inputForm = InputForm.describedBy(
            List.of(
                    "Сначала введите высоту N от 1 до 100.",
                    "Затем введите N строк: в строке 1 — одну цифру, в строке 2 — две и так далее.",
                    "Каждое значение должно быть цифрой от 0 до 9.",
                    "Пример для N=3: строки '1', '2 3', '4 5 6'."
            ),
            List.of(
                    new DigitTriangleField(
                            TREE,
                            "Высота N: ",
                            "Строка %d (%d цифр): "
                    )
            ),
            values -> values.get(TREE)
    );

    @Override
    public String taskId() {
        return ChristmasTreeTaskModule.TASK_ID;
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
