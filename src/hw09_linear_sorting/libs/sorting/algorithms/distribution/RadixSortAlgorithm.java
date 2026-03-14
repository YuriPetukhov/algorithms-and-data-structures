package hw09_linear_sorting.libs.sorting.algorithms.distribution;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

import java.util.Arrays;

public class RadixSortAlgorithm implements SortAlgorithm {

    private static final int RADIX = 256;
    private static final int MASK = RADIX - 1;

    @Override
    public void sort(int[] a, IntArrayOps ops) {

        int n = a.length;
        if (n <= 1) return;

        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) max = a[i];
        }

        int[] output = new int[n];
        int[] count = new int[RADIX];

        for (int shift = 0; (max >>> shift) > 0; shift += 8) {

            Arrays.fill(count, 0);

            for (int j : a) {
                int digit = (j >>> shift) & MASK;
                count[digit]++;
            }

            for (int i = 1; i < RADIX; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = (a[i] >>> shift) & MASK;
                int pos = --count[digit];
                output[pos] = a[i];
            }

            for (int i = 0; i < n; i++) {
                ops.write(a, i, output[i]);
            }
        }
    }
}