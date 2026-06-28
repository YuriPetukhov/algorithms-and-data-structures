package hw21_dynamic_programming.console.adapter.tasks.fractionsum;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.tasks.fractionsum.FractionSumTaskModule;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionInput;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionResult;

import java.util.List;

public final class FractionSumConsoleAdapter
        implements ConsoleTaskAdapter<FractionInput, FractionResult> {

    private static final InputKey<FractionInput> EXPRESSION =
            new InputKey<>("fraction-expression", FractionInput.class);

    private final InputForm<FractionInput> inputForm = InputForm.describedBy(
            List.of(
                    "Введите сумму двух дробей одной строкой в формате a/b+c/d.",
                    "Каждое число — от 1 до 10000; каждая дробь меньше 1, сумма не больше 1.",
                    "Пример ввода: 1/2+1/3"
            ),
            List.of(new FractionExpressionField(EXPRESSION, "Дроби: ")),
            values -> values.get(EXPRESSION)
    );

    @Override
    public String taskId() {
        return FractionSumTaskModule.TASK_ID;
    }

    @Override
    public Class<FractionInput> inputType() {
        return FractionInput.class;
    }

    @Override
    public Class<FractionResult> resultType() {
        return FractionResult.class;
    }

    @Override
    public InputForm<FractionInput> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<FractionResult> resultView() {
        return ResultView.of("Результат: ", FractionResult::toString);
    }
}
