package hw09_linear_sorting.test.middle;

public record BinarySortingRow(
        long n,
        long countingMs, boolean countingOk,
        long radixMs, boolean radixOk,
        long bucketMs, boolean bucketOk
) {
}