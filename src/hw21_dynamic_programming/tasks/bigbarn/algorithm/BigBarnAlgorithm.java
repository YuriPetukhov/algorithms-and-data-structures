package hw21_dynamic_programming.tasks.bigbarn.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.barn.algorithm.BarnMatrixFactory;
import hw21_dynamic_programming.tasks.barn.model.BarnInput;

import java.util.Objects;

public final class BigBarnAlgorithm implements Algorithm<BarnInput, Integer> {

    public static final String ID = "big-barn-algorithm";

    private final BarnMatrixFactory matrixFactory;
    private final BigBarnSolver solver;

    public BigBarnAlgorithm() {
        this(new BarnMatrixFactory(), new BigBarnSolver());
    }

    public BigBarnAlgorithm(
            BarnMatrixFactory matrixFactory,
            BigBarnSolver solver
    ) {
        this.matrixFactory = Objects.requireNonNull(
                matrixFactory,
                "Matrix factory must not be null."
        );
        this.solver = Objects.requireNonNull(solver, "Solver must not be null.");
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
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public Integer execute(BarnInput input) {
        return solver.solve(matrixFactory.create(input));
    }
}
