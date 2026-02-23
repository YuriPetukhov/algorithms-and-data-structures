package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.shell.ShellSortKnuthAlgorithm;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class ShellSortKnuthVariant implements SortingVariant {

    @Override public String id() { return "shell_sort_knuth"; }

    @Override public String displayName() { return "Сортировка: ShellSortKnuth"; }

    @Override
    public SortAlgorithm build(SortingParams params) {
        return new ShellSortKnuthAlgorithm();
    }
}