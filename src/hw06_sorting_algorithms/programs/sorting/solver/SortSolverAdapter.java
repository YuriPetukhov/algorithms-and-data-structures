package hw06_sorting_algorithms.programs.sorting.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.InterruptibleIntArrayOps;
import hw06_sorting_algorithms.libs.sorting.ops.PlainIntArrayOps;

import java.util.Arrays;

public final class SortSolverAdapter implements Solver<int[], int[]> {

    private final SortAlgorithm algorithm;
    private final boolean interruptible;

    public SortSolverAdapter(SortAlgorithm algorithm, boolean interruptible) {
        if (algorithm == null) throw new IllegalArgumentException("algorithm is null");
        this.algorithm = algorithm;
        this.interruptible = interruptible;
    }

    @Override
    public int[] solve(int[] input) {
        int[] workingCopy = Arrays.copyOf(input, input.length);

        if (interruptible) {
            algorithm.sort(workingCopy, new InterruptibleIntArrayOps(PlainIntArrayOps.INSTANCE));
        } else {
            algorithm.sort(workingCopy, PlainIntArrayOps.INSTANCE);
        }

        return workingCopy;
    }
}