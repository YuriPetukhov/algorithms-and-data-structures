package hw21_dynamic_programming.tasks.fractionsum.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.fractionsum.algorithm.FractionSumSolver;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionInput;
import hw21_dynamic_programming.tasks.fractionsum.model.FractionResult;

import java.util.Objects;

public final class FractionSumAlgorithm implements Algorithm<FractionInput, FractionResult> {

    public static final String ID = "fraction-sum";
    private final FractionSumSolver solver;

    public FractionSumAlgorithm() {
        this(new FractionSumSolver());
    }

    public FractionSumAlgorithm(FractionSumSolver solver) {
        this.solver = Objects.requireNonNull(solver, "Solver must not be null.");
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<FractionInput> inputType() {
        return FractionInput.class;
    }

    @Override
    public Class<FractionResult> resultType() {
        return FractionResult.class;
    }

    @Override
    public FractionResult execute(FractionInput input) {
        long[] result = solver.solve(
                input.firstNumerator(),
                input.firstDenominator(),
                input.secondNumerator(),
                input.secondDenominator()
        );
        return new FractionResult(result[0], result[1]);
    }
}
