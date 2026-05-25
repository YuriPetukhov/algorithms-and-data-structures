package hw20_rle_file_compression.benchmark;

public record BenchmarkResult(
        String caseName,
        String algorithm,
        int originalSize,
        int compressedSize,
        double compressionRatio,
        boolean restoredCorrectly,
        String originalHash,
        String restoredHash
) {
}