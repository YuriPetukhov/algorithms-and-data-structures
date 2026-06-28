package hw21_dynamic_programming.console.adapter.tasks.freeheight;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.format.TextFormatters;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.SparseBarnInputField;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.freeheight.FreeHeightTaskModule;

import java.util.List;

public final class FreeHeightConsoleAdapter
        implements ConsoleTaskAdapter<BarnInput, int[][]> {

    private static final InputKey<BarnInput> INPUT =
            new InputKey<>("free-height-input", BarnInput.class);

    private final InputForm<BarnInput> inputForm = InputForm.describedBy(
            List.of(
                    "Введите размеры N M одной строкой: N — ширина, M — высота поля (1..1000).",
                    "Затем введите количество занятых клеток T (0..10000).",
                    "После этого введите T строк с координатами X Y.",
                    "Пример: размеры '3 3', T='1', координата '1 0'."
            ),
            List.of(
                    new SparseBarnInputField(
                            INPUT,
                            "Размеры N M: ",
                            "Количество построек T: ",
                            "Постройка %d, координаты X Y: "
                    )
            ),
            values -> values.get(INPUT)
    );

    @Override
    public String taskId() {
        return FreeHeightTaskModule.TASK_ID;
    }

    @Override
    public Class<BarnInput> inputType() {
        return BarnInput.class;
    }

    @Override
    public Class<int[][]> resultType() {
        return int[][].class;
    }

    @Override
    public InputForm<BarnInput> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<int[][]> resultView() {
        return ResultView.of("Результат:\n", TextFormatters::integerMatrix);
    }
}
