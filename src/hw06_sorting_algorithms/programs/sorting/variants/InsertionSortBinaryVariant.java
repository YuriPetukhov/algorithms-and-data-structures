package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.insertion.InsertionSortBinaryAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class InsertionSortBinaryVariant implements SortingVariant {

    @Override public String id() { return "insertion_sort_binary"; }

    @Override public String displayName() { return "Сортировка: InsertionSort (binary)"; }

    @Override public SortAlgorithm build(SortingParams params) { return new InsertionSortBinaryAlgorithm(); }
}