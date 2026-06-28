package hw21_dynamic_programming.tasks.bigbarn;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.barn.BarnInputValidation;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.bigbarn.algorithm.BigBarnAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class BigBarnTaskModule
        extends AbstractTaskModule<BarnInput, Integer> {

    public static final String TASK_ID = "big-barn";

    public BigBarnTaskModule() {
        super(
                new BigBarnAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Большой сарай: максимальная площадь",
                        BigBarnAlgorithm.ID,
                        BarnInput.class,
                        Integer.class,
                        ValidationSchema.of(BarnInputValidation::validateSparse)
                )
        );
    }
}
