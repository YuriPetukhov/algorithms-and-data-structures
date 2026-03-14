package hw09_linear_sorting.programs.binary_sorting.variants;

import hw09_linear_sorting.programs.binary_sorting.solver.BinaryBucketSortSolver;
import hw09_linear_sorting.programs.binary_sorting.solver.BinarySortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

public class BucketSortVariant implements BinarySortingVariant {
    @Override
    public String id() {
        return "binary_bucket_sort";
    }

    @Override
    public String displayName() {
        return "Binary programs: BucketSort";
    }

    @Override
    public BinarySortSolver build(BinarySortingParams params) {
        return new BinaryBucketSortSolver(params);
    }
}
