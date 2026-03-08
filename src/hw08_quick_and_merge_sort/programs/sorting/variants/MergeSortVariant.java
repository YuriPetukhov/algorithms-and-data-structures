package hw08_quick_and_merge_sort.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw08_quick_and_merge_sort.libs.sorting.algorithms.merge.MergeSortAlgorithm;

public class MergeSortVariant implements SortingVariant {

    @Override public String id() { return "merge_sort"; }

    @Override public String displayName() { return "Сортировка: Merge"; }

    @Override
    public SortAlgorithm build(SortingParams params) {
        return new MergeSortAlgorithm();
    }
}
