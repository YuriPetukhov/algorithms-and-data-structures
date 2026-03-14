package hw09_linear_sorting.programs.distribution_sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw09_linear_sorting.libs.sorting.algorithms.distribution.RadixSortAlgorithm;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionVisualization;

public final class RadixSortVariant implements DistributionSortingVariant {

    @Override
    public String id() {
        return "radix_sort";
    }

    @Override
    public String displayName() {
        return "Сортировка: RadixSort";
    }

    @Override
    public SortAlgorithm build(DistributionSortingParams params) {
        return new RadixSortAlgorithm();
    }

    @Override
    public DistributionVisualization buildVisualization(DistributionSortingParams params) {
        return new RadixSortVisualization(params);
    }
}
