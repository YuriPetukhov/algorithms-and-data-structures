package hw09_linear_sorting.programs.distribution_sorting.spi;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw09_linear_sorting.programs.sorting.spi.Variant;

public interface DistributionSortingVariant extends Variant {
    SortAlgorithm build(DistributionSortingParams params);
    DistributionVisualization buildVisualization(DistributionSortingParams params);
}
