package hw06_sorting_algorithms.libs.sorting.algorithms.shell;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.gaps.GapSequence;
import hw06_sorting_algorithms.libs.sorting.gaps.HalvingGaps;

public final class ShellSortAlgorithm implements SortAlgorithm {

    private final GapSequence gaps;

    public ShellSortAlgorithm() {
        this(new HalvingGaps());
    }

    public ShellSortAlgorithm(GapSequence gaps) {
        if (gaps == null) throw new IllegalArgumentException("gaps is null");
        this.gaps = gaps;
    }

    public GapSequence gapSequence() { return gaps; }

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;

        for (int gap : gaps.gaps(n)) {
            for (int i = gap; i < n; i++) {
                int j = i;
                while (j >= gap && ops.gt(a, j - gap, j)) {
                    ops.swap(a, j - gap, j);
                    j -= gap;
                }
            }
        }
    }
}
