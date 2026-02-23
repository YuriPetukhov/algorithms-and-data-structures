package hw06_sorting_algorithms.libs.sorting.algorithms.shell;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class ShellSortKnuthAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;

        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int key = a[i];
                int j = i;

                while (j >= h && a[j - h] > key) {
                    ops.write(a, j, a[j - h]);
                    j -= h;
                }
                ops.write(a, j, key);
            }
            h /= 3;
        }
    }
}