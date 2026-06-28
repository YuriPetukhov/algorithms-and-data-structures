package hw21_dynamic_programming.tasks.widthbounds;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.barn.model.WidthBounds;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;
import hw21_dynamic_programming.tasks.widthbounds.algorithm.WidthBoundsAlgorithm;

public final class WidthBoundsTaskModule
        extends AbstractTaskModule<int[], WidthBounds> {

    public static final String TASK_ID = "barn-width-bounds";

    public WidthBoundsTaskModule() {
        super(
                new WidthBoundsAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Ширина сарая: границы L и R",
                        WidthBoundsAlgorithm.ID,
                        int[].class,
                        WidthBounds.class,
                        ValidationSchema.of(WidthBoundsTaskModule::validateHeights)
                )
        );
    }

    private static void validateHeights(int[] heights) {
        if (heights.length < 1 || heights.length > 10_000) {
            throw new IllegalArgumentException(
                    "Размер массива N должен находиться в диапазоне от 1 до 10000."
            );
        }
        for (int index = 0; index < heights.length; index++) {
            if (heights[index] < 0 || heights[index] > 10_000) {
                throw new IllegalArgumentException(
                        "A[%d] должно находиться в диапазоне от 0 до 10000."
                                .formatted(index)
                );
            }
        }
    }
}
