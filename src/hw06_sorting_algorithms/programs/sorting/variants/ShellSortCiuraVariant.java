package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.shell.ShellSortCiuraAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class ShellSortCiuraVariant implements SortingVariant {

    @Override public String id() { return "shell_sort_ciura"; }

    @Override public String displayName() { return "Сортировка: ShellSortCiura"; }

    @Override public SortAlgorithm build(SortingParams params) {
        return new ShellSortCiuraAlgorithm(); }
}