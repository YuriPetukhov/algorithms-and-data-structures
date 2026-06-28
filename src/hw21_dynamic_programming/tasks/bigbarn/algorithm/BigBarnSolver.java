package hw21_dynamic_programming.tasks.bigbarn.algorithm;

import hw21_dynamic_programming.tasks.barn.algorithm.FreeHeightCalculator;
import hw21_dynamic_programming.tasks.barn.algorithm.WidthBoundsCalculator;
import hw21_dynamic_programming.tasks.barn.model.WidthBounds;

import java.util.Objects;

public final class BigBarnSolver {

    private final FreeHeightCalculator heightCalculator;
    private final WidthBoundsCalculator widthCalculator;

    public BigBarnSolver() {
        this(new FreeHeightCalculator(), new WidthBoundsCalculator());
    }

    public BigBarnSolver(
            FreeHeightCalculator heightCalculator,
            WidthBoundsCalculator widthCalculator
    ) {
        this.heightCalculator = Objects.requireNonNull(
                heightCalculator,
                "Height calculator must not be null."
        );
        this.widthCalculator = Objects.requireNonNull(
                widthCalculator,
                "Width calculator must not be null."
        );
    }

    public int solve(boolean[][] blocked) {
        int[][] heights = heightCalculator.calculate(blocked);
        int bestArea = 0;

        for (int[] rowHeights : heights) {
            WidthBounds bounds = widthCalculator.calculate(rowHeights);
            for (int column = 0; column < rowHeights.length; column++) {
                int width = bounds.rightAt(column) - bounds.leftAt(column) + 1;
                bestArea = Math.max(bestArea, rowHeights[column] * width);
            }
        }
        return bestArea;
    }
}
