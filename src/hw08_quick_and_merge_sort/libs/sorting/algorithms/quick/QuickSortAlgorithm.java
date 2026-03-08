package hw08_quick_and_merge_sort.libs.sorting.algorithms.quick;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public class QuickSortAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] a, IntArrayOps ops) {
        quickSort(a, ops, 0, a.length - 1);
    }

    private static void quickSort(int[] a, IntArrayOps ops, int left, int right) {
        while (left < right) {
            int p = partitionHoareMedian3(a, ops, left, right);

            if ((p - left) < (right - (p + 1))) {
                quickSort(a, ops, left, p);
                left = p + 1;
            } else {
                quickSort(a, ops, p + 1, right);
                right = p;
            }
        }
    }

    private static int partitionHoareMedian3(int[] a, IntArrayOps ops, int left, int right) {
        int mid = left + ((right - left) >>> 1);

        if (ops.gt(a, left, mid)) ops.swap(a, left, mid);
        if (ops.gt(a, left, right)) ops.swap(a, left, right);
        if (ops.gt(a, mid, right)) ops.swap(a, mid, right);

        int pivot = a[mid];

        int i = left - 1;
        int j = right + 1;
        while (true) {
            do { i++; } while (a[i] < pivot);
            do { j--; } while (a[j] > pivot);
            if (i >= j) return j;
            ops.swap(a, i, j);
        }
    }
}
