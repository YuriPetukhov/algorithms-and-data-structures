package hw06_sorting_algorithms.programs.sorting.spi;

import hw06_sorting_algorithms.libs.sorting.gaps.GapSequence;
import hw06_sorting_algorithms.libs.sorting.gaps.HalvingGaps;

public record SortingParams(GapSequence gaps) {
    public static SortingParams defaults() {
        return new SortingParams(new HalvingGaps());
    }
}
