package hw09_linear_sorting.programs.distribution_sorting.trace;

import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingParams;
import hw09_linear_sorting.programs.distribution_sorting.trace.radix.RadixTraceRecorder;

public final class TracingRadixSortRunner {

    private TracingRadixSortRunner() {
    }

    public static void sort(
            int[] array,
            RadixTraceRecorder traceRecorder,
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

        if (array.length == 0) {
            traceRecorder.phase("Done", 1);
            return;
        }

        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingPassByDigit(array, traceRecorder, exp);
        }

        traceRecorder.phase("Done", 1);
    }

    private static void countingPassByDigit(
            int[] array,
            RadixTraceRecorder traceRecorder,
            int exp
    ) {
        int[] count = new int[10];
        int[] output = new int[array.length];

        traceRecorder.phase("Counting digits", exp);

        for (int inputIndex = 0; inputIndex < array.length; inputIndex++) {
            int value = array[inputIndex];
            int digit = (value / exp) % 10;

            traceRecorder.read(inputIndex, value, digit, exp);

            count[digit]++;
            traceRecorder.count(digit, count[digit], exp);
        }

        traceRecorder.phase("Building prefix sums", exp);

        for (int digit = 1; digit < count.length; digit++) {
            count[digit] += count[digit - 1];
            traceRecorder.prefix(digit, count[digit], exp);
        }

        traceRecorder.phase("Placing elements into output", exp);

        for (int inputIndex = array.length - 1; inputIndex >= 0; inputIndex--) {
            int value = array[inputIndex];
            int digit = (value / exp) % 10;

            traceRecorder.read(inputIndex, value, digit, exp);

            int outputIndex = count[digit] - 1;
            traceRecorder.place(inputIndex, value, digit, outputIndex, exp);

            output[outputIndex] = value;
            traceRecorder.writeOutput(outputIndex, value, exp);

            count[digit]--;
            traceRecorder.decrement(digit, count[digit], exp);
        }

        traceRecorder.phase("Copying output to main array", exp);

        for (int i = 0; i < output.length; i++) {
            array[i] = output[i];
            traceRecorder.copyBack(i, output[i], exp);
        }
    }
}