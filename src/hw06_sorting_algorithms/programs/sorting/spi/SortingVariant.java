package hw06_sorting_algorithms.programs.sorting.spi;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw09_linear_sorting.programs.sorting.spi.Variant;

public interface SortingVariant extends Variant {
    SortAlgorithm build(SortingParams params);
    default boolean supportsGaps() { return false; }
}