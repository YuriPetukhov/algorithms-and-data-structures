package hw06_sorting_algorithms.libs.sorting.algorithms.insertion;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class InsertionSortShiftAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                ops.write(a, j + 1, a[j]);
                j--;
            }

            ops.write(a, j + 1, key);
        }
    }
}