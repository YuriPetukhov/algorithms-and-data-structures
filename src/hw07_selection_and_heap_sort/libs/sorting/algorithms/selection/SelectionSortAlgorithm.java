package hw07_selection_and_heap_sort.libs.sorting.algorithms.selection;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public final class SelectionSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (ops.gt(a, min, j)) {
                    min = j;
                }
            }

            if (min != i) {
                ops.swap(a, i, min);
            }
        }
    }
}