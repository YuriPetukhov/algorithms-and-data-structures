package hw21_dynamic_programming.console.adapter.tasks.widthbounds;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.format.TextFormatters;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.SizedIntegerSequenceField;
import hw21_dynamic_programming.tasks.barn.model.WidthBounds;
import hw21_dynamic_programming.tasks.widthbounds.WidthBoundsTaskModule;

import java.util.List;

public final class WidthBoundsConsoleAdapter
        implements ConsoleTaskAdapter<int[], WidthBounds> {

    private static final InputKey<int[]> HEIGHTS =
            new InputKey<>("height-sequence", int[].class);

    private final InputForm<int[]> inputForm = InputForm.describedBy(
            List.of(
                    "Введите размер массива N от 1 до 10000.",
                    "Затем введите N значений A[i] от 0 до 10000, каждое на отдельной строке.",
                    "Пример: N=4, затем значения 2, 1, 2, 3."
            ),
            List.of(
                    new SizedIntegerSequenceField(
                            HEIGHTS,
                            "Размер N: ",
                            "A[%d]: ",
                            10_000,
                            0,
                            10_000
                    )
            ),
            values -> values.get(HEIGHTS)
    );

    @Override
    public String taskId() {
        return WidthBoundsTaskModule.TASK_ID;
    }

    @Override
    public Class<int[]> inputType() {
        return int[].class;
    }

    @Override
    public Class<WidthBounds> resultType() {
        return WidthBounds.class;
    }

    @Override
    public InputForm<int[]> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<WidthBounds> resultView() {
        return ResultView.of(
                "Результат:\n",
                bounds -> TextFormatters.integerArray(bounds.left())
                        + System.lineSeparator()
                        + TextFormatters.integerArray(bounds.right())
        );
    }
}
