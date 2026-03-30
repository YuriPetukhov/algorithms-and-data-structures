package hw06_sorting_algorithms.libs.sorting.algorithms.bubble;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

public class BubbleSortMovingMaxAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] a, IntArrayOps ops) {
        for (int end = a.length - 1; end > 0; end--) {
            int current = a[0];
            for (int i = 1; i <= end; i++) {
                if (ops.gt(a, i - 1, i)) {
                    ops.write(a, i - 1, a[i]);
                    ops.write(a, i, current);
                } else {
                    current = a[i];
                }
            }
        }
    }
}
