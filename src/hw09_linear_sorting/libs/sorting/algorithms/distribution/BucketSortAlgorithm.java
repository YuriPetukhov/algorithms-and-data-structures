package hw09_linear_sorting.libs.sorting.algorithms.distribution;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw06_sorting_algorithms.libs.sorting.ops.IntArrayOps;

import java.util.ArrayList;
import java.util.List;

public class BucketSortAlgorithm implements SortAlgorithm {

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

        int bucketCount = Math.max(1, (int) Math.sqrt(n));
        int range = max - min + 1;
        int bucketSize = Math.max(1, (range + bucketCount - 1) / bucketCount);

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int value : a) {
            int bucketIndex = (value - min) / bucketSize;
            if (bucketIndex >= bucketCount) {
                bucketIndex = bucketCount - 1;
            }
            buckets.get(bucketIndex).add(value);
        }

        int pos = 0;

        for (List<Integer> bucket : buckets) {
            insertionSort(bucket);

            for (int value : bucket) {
                ops.write(a, pos++, value);
            }
        }
    }

    private void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int key = bucket.get(i);
            int j = i - 1;

            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }

            bucket.set(j + 1, key);
        }
    }
}