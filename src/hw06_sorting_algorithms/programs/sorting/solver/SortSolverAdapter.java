package hw06_sorting_algorithms.programs.sorting.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.PlainIntArrayOps;

import java.util.Arrays;

public final class SortSolverAdapter implements Solver<int[], int[]> {

    private final SortAlgorithm sortAlgorithm;

    public SortSolverAdapter(SortAlgorithm sortAlgorithm) {
        if (sortAlgorithm == null) {
            throw new IllegalArgumentException("sortAlgorithm is null");
        }
        this.sortAlgorithm = sortAlgorithm;
    }

    @Override
    public int[] solve(int[] input) {
        int[] workingCopy = Arrays.copyOf(input, input.length);
        sortAlgorithm.sort(workingCopy, PlainIntArrayOps.INSTANCE);
        return workingCopy;
    }
}