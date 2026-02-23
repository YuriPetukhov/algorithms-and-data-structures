package hw06_sorting_algorithms.libs.sorting.algorithms.bubble;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class BubbleSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        for (int end = a.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (ops.gt(a, i, i + 1)) {
                    ops.swap(a, i, i + 1);
                }
            }
        }
    }
}
