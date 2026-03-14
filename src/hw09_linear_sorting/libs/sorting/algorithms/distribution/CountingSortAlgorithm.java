package hw09_linear_sorting.libs.sorting.algorithms.distribution;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public class CountingSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;
        if (n <= 1) return;

        int min = a[0];
        int max = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }

        int[] count = new int[max - min + 1];

        for (int i = 0; i < n; i++) {
            count[a[i] - min]++;
        }

        int pos = 0;

        for (int value = 0; value < count.length; value++) {
            int c = count[value];

            for (int k = 0; k < c; k++) {
                ops.write(a, pos++, value + min);
            }
        }
    }
}