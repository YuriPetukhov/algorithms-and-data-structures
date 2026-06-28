package hw21_dynamic_programming.tasks.fiveeight;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.fiveeight.algorithm.FiveEightCountAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

import java.math.BigInteger;

public final class FiveEightTaskModule
        extends AbstractTaskModule<Integer, BigInteger> {

    public static final String TASK_ID = "five-eight-count";

    public FiveEightTaskModule() {
        super(
                new FiveEightCountAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Пятью восемь",
                        FiveEightCountAlgorithm.ID,
                        Integer.class,
                        BigInteger.class,
                        ValidationSchema.of(FiveEightTaskModule::validateLength)
                )
        );
    }

    private static void validateLength(Integer length) {
        if (length < 1 || length > 88) {
            throw new IllegalArgumentException(
                    "N должно находиться в диапазоне от 1 до 88."
            );
        }
    }
}
