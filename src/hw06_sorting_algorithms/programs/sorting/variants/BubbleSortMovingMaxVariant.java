package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.bubble.BubbleSortMovingMaxAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public class BubbleSortMovingMaxVariant implements SortingVariant {
    @Override
    public SortAlgorithm build(SortingParams params) {
        return new BubbleSortMovingMaxAlgorithm();
    }

    @Override
    public String id() {
        return "bubble_sort_moving_max";
    }

    @Override
    public String displayName() {
        return "Сортировка: BubbleSortMovingMax (moving max)";
    }
}
