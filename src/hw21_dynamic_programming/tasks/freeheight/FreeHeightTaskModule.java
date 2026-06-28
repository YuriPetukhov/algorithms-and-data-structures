package hw21_dynamic_programming.tasks.freeheight;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.barn.BarnInputValidation;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;
import hw21_dynamic_programming.tasks.freeheight.algorithm.FreeHeightAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class FreeHeightTaskModule
        extends AbstractTaskModule<BarnInput, int[][]> {

    public static final String TASK_ID = "barn-free-height";

    public FreeHeightTaskModule() {
        super(
                new FreeHeightAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Длина сарая: свободные клетки вверх",
                        FreeHeightAlgorithm.ID,
                        BarnInput.class,
                        int[][].class,
                        ValidationSchema.of(BarnInputValidation::validateSparse)
                )
        );
    }
}
