package hw21_dynamic_programming.tasks.christmastree;

import hw21_dynamic_programming.tasks.api.SimpleTaskDefinition;
import hw21_dynamic_programming.tasks.christmastree.algorithm.ChristmasTreeMaxPathAlgorithm;
import hw21_dynamic_programming.tasks.spi.AbstractTaskModule;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

public final class ChristmasTreeTaskModule
        extends AbstractTaskModule<int[][], Integer> {

    public static final String TASK_ID = "christmas-tree-max-path";

    public ChristmasTreeTaskModule() {
        super(
                new ChristmasTreeMaxPathAlgorithm(),
                SimpleTaskDefinition.usingDefaultPipeline(
                        TASK_ID,
                        "Ёлочка программиста",
                        ChristmasTreeMaxPathAlgorithm.ID,
                        int[][].class,
                        Integer.class,
                        ValidationSchema.of(ChristmasTreeTaskModule::validateTree)
                )
        );
    }

    private static void validateTree(int[][] tree) {
        if (tree.length < 1 || tree.length > 100) {
            throw new IllegalArgumentException(
                    "Высота ёлочки должна находиться в диапазоне от 1 до 100."
            );
        }
        for (int row = 0; row < tree.length; row++) {
            if (tree[row] == null || tree[row].length != row + 1) {
                throw new IllegalArgumentException(
                        "Строка ёлочки %d должна содержать %d цифр."
                                .formatted(row + 1, row + 1)
                );
            }
            for (int digit : tree[row]) {
                if (digit < 0 || digit > 9) {
                    throw new IllegalArgumentException(
                            "Ёлочка должна состоять из цифр от 0 до 9."
                    );
                }
            }
        }
    }
}
