package hw21_dynamic_programming.tasks.smallbarn.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.smallbarn.algorithm.SmallBarnBruteForceSolver;

import java.util.Objects;

public final class SmallBarnAlgorithm implements Algorithm<boolean[][], Integer> {

    public static final String ID = "small-barn";
    private final SmallBarnBruteForceSolver solver;

    public SmallBarnAlgorithm() {
        this(new SmallBarnBruteForceSolver());
    }

    public SmallBarnAlgorithm(SmallBarnBruteForceSolver solver) {
        this.solver = Objects.requireNonNull(solver, "Solver must not be null.");
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<boolean[][]> inputType() {
        return boolean[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public Integer execute(boolean[][] input) {
        return solver.solve(input);
    }
}
