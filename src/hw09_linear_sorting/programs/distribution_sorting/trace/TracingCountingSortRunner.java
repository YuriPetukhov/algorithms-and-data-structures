package hw09_linear_sorting.programs.distribution_sorting.trace;

import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.counting.CountingTraceRecorder;

public final class TracingCountingSortRunner {

    private TracingCountingSortRunner() {
    }

    public static void sort(
            int[] array,
            CountingTraceRecorder traceRecorder,
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

        int range = maxValue - minValue + 1;
        int[] count = new int[range];
        int[] output = new int[array.length];

        traceRecorder.phase("Counting frequencies");

        for (int index = 0; index < array.length; index++) {
            int value = array[index];

            if (value < minValue || value > maxValue) {
                throw new IllegalArgumentException(
                        "Value out of configured range: a[" + index + "]=" + value
                                + ", expected in [" + minValue + ".." + maxValue + "]"
                );
            }

            traceRecorder.read(index, value);

            int key = value - minValue;
            count[key]++;

            traceRecorder.count(key, count[key]);
        }

        traceRecorder.phase("Building prefix sums");

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            traceRecorder.prefix(i, count[i]);
        }

        traceRecorder.phase("Placing elements into output");

        for (int inputIndex = array.length - 1; inputIndex >= 0; inputIndex--) {
            int value = array[inputIndex];
            int key = value - minValue;

            traceRecorder.read(inputIndex, value);

            int outputIndex = count[key] - 1;
            traceRecorder.place(inputIndex, value, outputIndex);

            output[outputIndex] = value;
            traceRecorder.writeOutput(outputIndex, value);

            count[key]--;
            traceRecorder.decrement(key, count[key]);
        }

        traceRecorder.phase("Copying output to main array");

        for (int index = 0; index < output.length; index++) {
            array[index] = output[index];
            traceRecorder.copyBack(index, output[index]);
        }

        traceRecorder.phase("Done");
    }
}
