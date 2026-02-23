package hw06_sorting_algorithms.libs.sorting.algorithms.insertion;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class InsertionSortBinaryAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] a, IntArrayOps ops) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];

            int pos = lowerBound(a, 0, i, key);

            for (int j = i - 1; j >= pos; j--) {
                ops.write(a, j + 1, a[j]);
            }

            ops.write(a, pos, key);
        }
    }

    private static int lowerBound(int[] a, int lo, int hi, int key) {
        int l = lo;
        int r = hi;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < key) l = m + 1;
            else r = m;
        }
        return l;
    }
}