package hw06_sorting_algorithms.libs.sorting.algorithms.bubble;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class BubbleSortOptimizedAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int end = a.length - 1;
        while (end > 0) {
            int lastSwap = 0;
            for (int i = 0; i < end; i++) {
                if (ops.gt(a, i, i + 1)) {
                    ops.swap(a, i, i + 1);
                    lastSwap = i;
                }
            }
            end = lastSwap;
        }
    }
}