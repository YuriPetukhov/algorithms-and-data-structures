package hw06_sorting_algorithms.libs.sorting.algorithms.shell;

import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;
import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;

public final class ShellSortCiuraAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;

        int[] gaps = ciuraGapsUpTo(n);

        for (int gi = gaps.length - 1; gi >= 0; gi--) {
            int h = gaps[gi];

            for (int i = h; i < n; i++) {
                int key = a[i];
                int j = i;

                while (j >= h && a[j - h] > key) {
                    ops.write(a, j, a[j - h]);
                    j -= h;
                }
                ops.write(a, j, key);
            }
        }
    }

    private static int[] ciuraGapsUpTo(int n) {
        int[] base = {1, 4, 10, 23, 57, 132, 301, 701, 1750};

        int count = 0;
        while (count < base.length && base[count] < n) count++;
        if (count > 0) {
            int[] g = new int[count];
            System.arraycopy(base, 0, g, 0, count);
            return g;
        }

        return new int[]{1};
    }
}