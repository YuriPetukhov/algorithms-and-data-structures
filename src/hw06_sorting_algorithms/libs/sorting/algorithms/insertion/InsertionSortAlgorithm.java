package hw06_sorting_algorithms.libs.sorting.algorithms.insertion;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class InsertionSortAlgorithm implements SortAlgorithm {
    public void sort(int[] a, IntArrayOps ops) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && ops.gt(a, j - 1, j)) {
                ops.swap(a, j - 1, j);
                j--;
            }
        }
    }
}
