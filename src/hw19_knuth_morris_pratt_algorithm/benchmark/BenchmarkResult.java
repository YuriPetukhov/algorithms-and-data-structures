package hw19_knuth_morris_pratt_algorithm.benchmark;

public record BenchmarkResult(
        String caseName,
        String algorithm,
        int textLength,
        int patternLength,
        int iterations,
        long averageTimeNs,
        int comparisons,
        int index
) {
}