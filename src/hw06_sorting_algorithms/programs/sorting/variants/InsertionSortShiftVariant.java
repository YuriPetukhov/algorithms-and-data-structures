package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.insertion.InsertionSortShiftAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class InsertionSortShiftVariant implements SortingVariant {

    @Override public String id() { return "insertion_sort_shift"; }

    @Override public String displayName() { return "Сортировка: InsertionSort (shift)"; }

    @Override public SortAlgorithm build(SortingParams params) { return new InsertionSortShiftAlgorithm(); }
}