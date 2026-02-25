package hw07_selection_and_heap_sort.libs.sorting.algorithms.heap;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public final class HeapSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(a, ops, i, n);
        }

        for (int end = n - 1; end > 0; end--) {
            ops.swap(a, 0, end);
            siftDown(a, ops, 0, end);
        }
    }

    private static void siftDown(int[] a, IntArrayOps ops, int i, int size) {
        while (true) {
            int left = 2 * i + 1;
            if (left >= size) return;

            int right = left + 1;

            int largest = left;
            if (right < size && ops.gt(a, right, left)) {
                largest = right;
            }

            if (!ops.gt(a, largest, i)) {
                return;
            }

            ops.swap(a, i, largest);
            i = largest;
        }
    }
}