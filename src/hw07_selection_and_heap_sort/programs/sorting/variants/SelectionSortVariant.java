package hw07_selection_and_heap_sort.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw07_selection_and_heap_sort.libs.sorting.algorithms.selection.SelectionSortAlgorithm;

public final class SelectionSortVariant implements SortingVariant {

    @Override public String id() { return "selection_sort"; }

    @Override public String displayName() { return "Сортировка: Selection"; }

    @Override
    public SortAlgorithm build(SortingParams params) {
        return new SelectionSortAlgorithm();
    }
}