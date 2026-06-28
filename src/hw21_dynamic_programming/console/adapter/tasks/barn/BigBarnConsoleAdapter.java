package hw21_dynamic_programming.console.adapter.tasks.barn;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.SparseBarnInputField;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.bigbarn.BigBarnTaskModule;

import java.util.List;

public final class BigBarnConsoleAdapter
        implements ConsoleTaskAdapter<BarnInput, Integer> {

    private static final InputKey<BarnInput> INPUT =
            new InputKey<>("big-barn-input", BarnInput.class);

    private final InputForm<BarnInput> inputForm = InputForm.describedBy(
            List.of(
                    "Введите размеры N M одной строкой: N — ширина, M — высота поля (1..1000).",
                    "Затем введите количество занятых клеток T (0..10000).",
                    "После этого введите T строк с координатами X Y, где 0 <= X < N и 0 <= Y < M.",
                    "Пример: размеры '4 3', T='2', координаты '2 0' и '2 1'."
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
        return BigBarnTaskModule.TASK_ID;
    }

    @Override
    public Class<BarnInput> inputType() {
        return BarnInput.class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public InputForm<BarnInput> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<Integer> resultView() {
        return ResultView.of("Результат: ", String::valueOf);
    }
}
