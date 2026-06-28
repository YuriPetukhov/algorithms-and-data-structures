package hw21_dynamic_programming.tasks.freeheight.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.barn.algorithm.BarnMatrixFactory;
import hw21_dynamic_programming.tasks.barn.algorithm.FreeHeightCalculator;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;

import java.util.Objects;

public final class FreeHeightAlgorithm implements Algorithm<BarnInput, int[][]> {

    public static final String ID = "barn-free-height-algorithm";

    private final BarnMatrixFactory matrixFactory;
    private final FreeHeightCalculator calculator;

    public FreeHeightAlgorithm() {
        this(new BarnMatrixFactory(), new FreeHeightCalculator());
    }

    public FreeHeightAlgorithm(
            BarnMatrixFactory matrixFactory,
            FreeHeightCalculator calculator
    ) {
        this.matrixFactory = Objects.requireNonNull(
                matrixFactory,
                "Matrix factory must not be null."
        );
        this.calculator = Objects.requireNonNull(
                calculator,
                "Height calculator must not be null."
        );
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<BarnInput> inputType() {
        return BarnInput.class;
    }

    @Override
    public Class<int[][]> resultType() {
        return int[][].class;
    }

    @Override
    public int[][] execute(BarnInput input) {
        return calculator.calculate(matrixFactory.create(input));
    }
}
