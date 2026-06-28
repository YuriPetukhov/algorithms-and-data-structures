package hw21_dynamic_programming.console.adapter.tasks.fiveeight;

import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.InputKey;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.model.field.RangedIntegerField;
import hw21_dynamic_programming.tasks.fiveeight.FiveEightTaskModule;

import java.math.BigInteger;
import java.util.List;

public final class FiveEightConsoleAdapter
        implements ConsoleTaskAdapter<Integer, BigInteger> {

    private static final InputKey<Integer> LENGTH =
            new InputKey<>("length", Integer.class);

    private final InputForm<Integer> inputForm = InputForm.describedBy(
            List.of(
                    "Введите натуральное число N от 1 до 88.",
                    "Программа посчитает N-значные числа из цифр 5 и 8 без трех одинаковых цифр подряд.",
                    "Пример ввода: 3"
            ),
            List.of(
                    new RangedIntegerField(
                            LENGTH,
                            "N: ",
                            1,
                            88,
                            "N"
                    )
            ),
            values -> values.get(LENGTH)
    );

    @Override
    public String taskId() {
        return FiveEightTaskModule.TASK_ID;
    }

    @Override
    public Class<Integer> inputType() {
        return Integer.class;
    }

    @Override
    public Class<BigInteger> resultType() {
        return BigInteger.class;
    }

    @Override
    public InputForm<Integer> inputForm() {
        return inputForm;
    }

    @Override
    public ResultView<BigInteger> resultView() {
        return ResultView.of("Результат: ", BigInteger::toString);
    }
}
