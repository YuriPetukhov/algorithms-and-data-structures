package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.bubble.BubbleSortOptimizedAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class BubbleSortOptimizedVariant implements SortingVariant {

    @Override public String id() { return "bubble_sort_optimized"; }

    @Override public String displayName() { return "Сортировка: BubbleSort (optimized)"; }

    @Override public SortAlgorithm build(SortingParams params) {
        return new BubbleSortOptimizedAlgorithm(); }
}