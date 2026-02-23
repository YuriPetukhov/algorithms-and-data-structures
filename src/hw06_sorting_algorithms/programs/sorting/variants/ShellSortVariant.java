package hw06_sorting_algorithms.programs.sorting.variants;

import hw06_sorting_algorithms.libs.sorting.algorithms.shell.ShellSortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.gaps.GapSequence;
import hw06_sorting_algorithms.libs.sorting.gaps.HalvingGaps;
import hw06_sorting_algorithms.programs.sorting.spi.SortingParams;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;

public final class ShellSortVariant implements SortingVariant {

    @Override public String id() { return "shell_sort"; }

    @Override public String displayName() { return "Сортировка: ShellSort"; }

    @Override public boolean supportsGaps() { return true; }

    @Override public SortAlgorithm build(SortingParams params) {
        GapSequence g = (params == null || params.gaps() == null) ? new HalvingGaps() : params.gaps();
        return new ShellSortAlgorithm(g); }
}