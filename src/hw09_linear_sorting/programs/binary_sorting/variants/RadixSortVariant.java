package hw09_linear_sorting.programs.binary_sorting.variants;

import hw09_linear_sorting.programs.binary_sorting.solver.BinaryRadixSortSolver;
import hw09_linear_sorting.programs.binary_sorting.solver.BinarySortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public class RadixSortVariant implements BinarySortingVariant {
    @Override
    public String id() {
        return "binary_radix_sort";
    }

    @Override
    public String displayName() {
        return "Binary programs: RadixSort";
    }

    @Override
    public BinarySortSolver build(BinarySortingParams params) {
        return new BinaryRadixSortSolver(params);
    }
}
