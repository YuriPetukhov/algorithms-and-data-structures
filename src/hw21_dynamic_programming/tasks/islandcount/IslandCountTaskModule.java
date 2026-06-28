package hw21_dynamic_programming.tasks.islandcount;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.islandcount.algorithm.IslandCountAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class IslandCountTaskModule
        extends AbstractTaskModule<int[][], Integer> {

    public static final String TASK_ID = "island-count";

    public IslandCountTaskModule() {
        super(
                new IslandCountAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Большой остров",
                        IslandCountAlgorithm.ID,
                        int[][].class,
                        Integer.class,
                        ValidationSchema.of(IslandCountTaskModule::validateMatrix)
                )
        );
    }

    private static void validateMatrix(int[][] matrix) {
        if (matrix.length < 1 || matrix.length > 100) {
            throw new IllegalArgumentException(
                    "Размер N должен находиться в диапазоне от 1 до 100."
            );
        }
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row] == null || matrix[row].length != matrix.length) {
                throw new IllegalArgumentException("Матрица должна быть квадратной N × N.");
            }
            for (int column = 0; column < matrix.length; column++) {
                int value = matrix[row][column];
                if (value != 0 && value != 1) {
                    throw new IllegalArgumentException(
                            "Ячейка [%d, %d] должна содержать 0 или 1."
                                    .formatted(row, column)
                    );
                }
            }
        }
    }
}
