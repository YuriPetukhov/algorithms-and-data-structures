package hw21_dynamic_programming.tasks.fractionsum;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.fractionsum.algorithm.FractionSumAlgorithm;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionInput;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionResult;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class FractionSumTaskModule
        extends AbstractTaskModule<FractionInput, FractionResult> {

    public static final String TASK_ID = "fraction-sum";

    public FractionSumTaskModule() {
        super(
                new FractionSumAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Раз горох, два горох",
                        FractionSumAlgorithm.ID,
                        FractionInput.class,
                        FractionResult.class,
                        ValidationSchema.of(FractionSumTaskModule::validateInput)
                )
        );
    }

    private static void validateInput(FractionInput input) {
        validateNumber(input.firstNumerator(), "a");
        validateNumber(input.firstDenominator(), "b");
        validateNumber(input.secondNumerator(), "c");
        validateNumber(input.secondDenominator(), "d");

        if (input.firstNumerator() >= input.firstDenominator()
                || input.secondNumerator() >= input.secondDenominator()) {
            throw new IllegalArgumentException(
                    "Каждая исходная дробь должна быть меньше единицы."
            );
        }

        long numerator = (long) input.firstNumerator() * input.secondDenominator()
                + (long) input.secondNumerator() * input.firstDenominator();
        long denominator = (long) input.firstDenominator() * input.secondDenominator();
        if (numerator > denominator) {
            throw new IllegalArgumentException(
                    "Сумма дробей не должна быть больше единицы."
            );
        }
    }

    private static void validateNumber(int value, String name) {
        if (value < 1 || value > 10_000) {
            throw new IllegalArgumentException(
                    "%s должно находиться в диапазоне от 1 до 10000."
                            .formatted(name)
            );
        }
    }
}
