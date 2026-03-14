package hw09_linear_sorting.programs.distribution_sorting.trace;

import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.bucket.BucketTraceRecorder;

import java.util.ArrayList;
import java.util.List;

public final class TracingBucketSortRunner {

    private TracingBucketSortRunner() {
    }

    public static void sort(
            int[] array,
            BucketTraceRecorder traceRecorder,
            DistributionSortingParams params
    ) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (traceRecorder == null) {
            throw new IllegalArgumentException("traceRecorder is null");
        }
        if (params == null) {
            throw new IllegalArgumentException("params is null");
        }

        int minValue = params.minValue();
        int maxValue = params.maxValue();

        if (minValue > maxValue) {
            throw new IllegalArgumentException("minValue > maxValue");
        }

        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (value < minValue || value > maxValue) {
                throw new IllegalArgumentException(
                        "Value out of configured range: a[" + i + "]=" + value
                                + ", expected in [" + minValue + ".." + maxValue + "]"
                );
            }
        }

        int n = array.length;
        if (n == 0) {
            traceRecorder.phase("Done");
            return;
        }

        int bucketCount = Math.max(1, (int) Math.sqrt(n));
        int range = maxValue - minValue + 1;
        int bucketSize = Math.max(1, (range + bucketCount - 1) / bucketCount);

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        traceRecorder.phase("Distributing into buckets");

        for (int i = 0; i < n; i++) {
            int value = array[i];
            traceRecorder.read(i, value);

            int bucketIndex = bucketIndex(value, minValue, bucketSize, bucketCount);
            List<Integer> bucket = buckets.get(bucketIndex);

            bucket.add(value);
            traceRecorder.place(i, value, bucketIndex, bucket);
        }

        traceRecorder.phase("Sorting buckets");

        for (int bucketIndex = 0; bucketIndex < bucketCount; bucketIndex++) {
            List<Integer> bucket = buckets.get(bucketIndex);

            if (bucket.isEmpty()) {
                continue;
            }

            traceRecorder.sortStart(bucketIndex, bucket);
            insertionSort(bucket, bucketIndex, traceRecorder);
        }

        traceRecorder.phase("Writing merged output");

        int outputIndex = 0;

        for (int bucketIndex = 0; bucketIndex < bucketCount; bucketIndex++) {
            List<Integer> bucket = buckets.get(bucketIndex);

            while (!bucket.isEmpty()) {
                int value = bucket.remove(0);
                array[outputIndex] = value;

                traceRecorder.flush(bucketIndex, outputIndex, value, bucket);

                outputIndex++;
            }
        }

        traceRecorder.phase("Done");
    }

    private static int bucketIndex(
            int value,
            int minValue,
            int bucketSize,
            int bucketCount
    ) {
        if (bucketCount == 1) {
            return 0;
        }

        int index = (value - minValue) / bucketSize;
        if (index >= bucketCount) {
            index = bucketCount - 1;
        }
        return index;
    }

    private static void insertionSort(
            List<Integer> bucket,
            int bucketIndex,
            BucketTraceRecorder traceRecorder
    ) {
        for (int i = 1; i < bucket.size(); i++) {
            int key = bucket.get(i);
            int j = i - 1;

            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                traceRecorder.sortStep(
                        bucketIndex,
                        bucket,
                        "Move " + bucket.get(j) + " right"
                );
                j--;
            }

            bucket.set(j + 1, key);
            traceRecorder.sortStep(
                    bucketIndex,
                    bucket,
                    "Insert " + key + " at position " + (j + 1)
            );
        }
    }
}