package hw07_selection_and_heap_sort.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw07_selection_and_heap_sort.libs.sorting.algorithms.heap.HeapSortAlgorithm;

public final class HeapSortVariant implements SortingVariant {

    @Override public String id() { return "heap_sort"; }

    @Override public String displayName() { return "Сортировка: Heap"; }

    @Override
    public SortAlgorithm build(SortingParams params) {
        return new HeapSortAlgorithm();
    }
}