package hw09_linear_sorting.programs.distribution_sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw09_linear_sorting.libs.sorting.algorithms.distribution.BucketSortAlgorithm;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionVisualization;

public final class BucketSortVariant implements DistributionSortingVariant {

    @Override
    public String id() {
        return "bucket_sort";
    }

    @Override
    public String displayName() {
        return "Сортировка: BucketSort";
    }

    @Override
    public SortAlgorithm build(DistributionSortingParams params) {
        return new BucketSortAlgorithm();
    }

    @Override
    public DistributionVisualization buildVisualization(DistributionSortingParams params) {
        return new BucketSortVisualization(params);
    }
}
