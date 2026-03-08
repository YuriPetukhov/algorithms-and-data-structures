package hw08_quick_and_merge_sort.programs.external_sorting.solver;

import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortJob;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortResult;

public interface ExternalSortSolver {
    ExternalSortResult solve(ExternalSortJob job) throws Exception;
}