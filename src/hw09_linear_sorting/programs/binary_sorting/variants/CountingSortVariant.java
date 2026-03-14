package hw09_linear_sorting.programs.binary_sorting.variants;

import hw09_linear_sorting.programs.binary_sorting.solver.BinaryCountingSortSolver;
import hw09_linear_sorting.programs.binary_sorting.solver.BinarySortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public class CountingSortVariant implements BinarySortingVariant {
    @Override
    public String id() {
        return "binary_counting_sort";
    }

    @Override
    public String displayName() {
        return "Binary programs: CountingSort";
    }

    @Override
    public BinarySortSolver build(BinarySortingParams params) {
        return new BinaryCountingSortSolver(params);
    }
}
