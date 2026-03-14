package hw09_linear_sorting.test.junior;

public record ArrayBenchmarkRow(
        int n,
        long countingMs, boolean countingOk,
        long radixMs, boolean radixOk,
        long bucketMs, boolean bucketOk
) {
}