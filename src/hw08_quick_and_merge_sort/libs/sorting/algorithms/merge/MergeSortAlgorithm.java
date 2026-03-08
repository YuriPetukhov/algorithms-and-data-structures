package hw08_quick_and_merge_sort.libs.sorting.algorithms.merge;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

import java.util.Arrays;

public final class MergeSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] a, IntArrayOps ops) {
        if (a.length <= 1) return;

        int[] buf = Arrays.copyOf(a, a.length);
        mergeSort(buf, a, ops, 0, a.length);
    }

    private static void mergeSort(int[] src, int[] dst, IntArrayOps ops, int lo, int hi) {
        int len = hi - lo;
        if (len <= 1) return;

        int mid = lo + (len >>> 1);

        mergeSort(dst, src, ops, lo, mid);
        mergeSort(dst, src, ops, mid, hi);

        if (src[mid - 1] <= src[mid]) {
            for (int i = lo; i < hi; i++) ops.write(dst, i, src[i]);
            return;
        }

        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi) {
            if (src[i] <= src[j]) ops.write(dst, k++, src[i++]);
            else ops.write(dst, k++, src[j++]);
        }
        while (i < mid) ops.write(dst, k++, src[i++]);
        while (j < hi) ops.write(dst, k++, src[j++]);
    }
}
