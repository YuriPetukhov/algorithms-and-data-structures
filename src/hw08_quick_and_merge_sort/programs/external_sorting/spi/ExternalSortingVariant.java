package hw08_quick_and_merge_sort.programs.external_sorting.spi;

import hw08_quick_and_merge_sort.programs.external_sorting.solver.ExternalSortSolver;

public interface ExternalSortingVariant {
    String id();
    String displayName();

    ExternalSortSolver build(ExternalSortingParams params);
}
