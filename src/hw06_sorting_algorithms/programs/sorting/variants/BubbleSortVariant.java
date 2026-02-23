package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.bubble.BubbleSortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class BubbleSortVariant implements SortingVariant {

    @Override public String id() { return "bubble_sort"; }

    @Override public String displayName() { return "Сортировка: BubbleSort (naive)"; }

    @Override
    public SortAlgorithm build(SortingParams params) {
        return new BubbleSortAlgorithm();
    }
}