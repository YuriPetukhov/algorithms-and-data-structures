package hw18_boyer_moore_algorithm.benchmark;

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