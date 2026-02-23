package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.insertion.InsertionSortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class InsertionSortVariant implements SortingVariant {

    @Override public String id() { return "insertion_sort"; }

    @Override public String displayName() { return "Сортировка: InsertionSort"; }

    @Override public SortAlgorithm build(SortingParams params) { return new InsertionSortAlgorithm(); }
}