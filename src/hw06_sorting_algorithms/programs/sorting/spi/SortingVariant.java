package hw06_sorting_algorithms.programs.sorting.spi;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public interface SortingVariant {
    String id();

    String displayName();

    SortAlgorithm build(SortingParams params);

    default boolean supportsGaps() { return false; }
}