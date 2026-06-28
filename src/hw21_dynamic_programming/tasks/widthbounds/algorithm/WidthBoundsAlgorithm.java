package hw21_dynamic_programming.tasks.widthbounds.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.barn.algorithm.WidthBoundsCalculator;
import hw21_dynamic_programming.tasks.barn.model.WidthBounds;

import java.util.Objects;

public final class WidthBoundsAlgorithm
        implements Algorithm<int[], WidthBounds> {

    public static final String ID = "barn-width-bounds-algorithm";

    private final WidthBoundsCalculator calculator;

    public WidthBoundsAlgorithm() {
        this(new WidthBoundsCalculator());
    }

    public WidthBoundsAlgorithm(WidthBoundsCalculator calculator) {
        this.calculator = Objects.requireNonNull(
                calculator,
                "Width calculator must not be null."
        );
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<int[]> inputType() {
        return int[].class;
    }

    @Override
    public Class<WidthBounds> resultType() {
        return WidthBounds.class;
    }

    @Override
    public WidthBounds execute(int[] input) {
        return calculator.calculate(input);
    }
}
