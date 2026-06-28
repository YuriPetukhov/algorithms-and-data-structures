package hw21_dynamic_programming.tasks.smallbarn;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.barn.BarnInputValidation;
import hw21_dynamic_programming.tasks.smallbarn.algorithm.SmallBarnAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class SmallBarnTaskModule
        extends AbstractTaskModule<boolean[][], Integer> {

    public static final String TASK_ID = "small-barn";

    public SmallBarnTaskModule() {
        super(
                new SmallBarnAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Маленький сарай: полный перебор",
                        SmallBarnAlgorithm.ID,
                        boolean[][].class,
                        Integer.class,
                        ValidationSchema.of(
                                input -> BarnInputValidation.validateDense(input, 30, 30)
                        )
                )
        );
    }
}
