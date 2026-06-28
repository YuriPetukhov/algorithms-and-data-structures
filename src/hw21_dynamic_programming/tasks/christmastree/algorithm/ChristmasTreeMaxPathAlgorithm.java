package hw21_dynamic_programming.tasks.christmastree.algorithm;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.christmastree.algorithm.ChristmasTreeMaxPathSolver;

import java.util.Objects;

public final class ChristmasTreeMaxPathAlgorithm implements Algorithm<int[][], Integer> {

    public static final String ID = "christmas-tree-max-path";
    private final ChristmasTreeMaxPathSolver solver;

    public ChristmasTreeMaxPathAlgorithm() {
        this(new ChristmasTreeMaxPathSolver());
    }

    public ChristmasTreeMaxPathAlgorithm(ChristmasTreeMaxPathSolver solver) {
        this.solver = Objects.requireNonNull(solver, "Solver must not be null.");
    }

    @Override
    public String id() {
        return ID;
    }

    @Override
    public Class<int[][]> inputType() {
        return int[][].class;
    }

    @Override
    public Class<Integer> resultType() {
        return Integer.class;
    }

    @Override
    public Integer execute(int[][] input) {
        return solver.solve(input);
    }
}
