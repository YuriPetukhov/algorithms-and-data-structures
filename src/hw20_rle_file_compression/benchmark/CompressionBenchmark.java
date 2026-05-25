package hw20_rle_file_compression.benchmark;

import hw20_rle_file_compression.libs.compression.Compressor;
import hw20_rle_file_compression.libs.hash.HashUtils;

import java.io.IOException;
import java.nio.file.Files;

public class CompressionBenchmark {
    public BenchmarkResult benchmark(
            BenchmarkCase benchmarkCase,
            Compressor compressor
    ) {
        try {
            byte[] input = Files.readAllBytes(benchmarkCase.inputPath());

            byte[] compressed = compressor.compress(input);
            byte[] restored = compressor.decompress(compressed);

            String originalHash = HashUtils.sha256(input);
            String restoredHash = HashUtils.sha256(restored);

            boolean restoredCorrectly = originalHash.equals(restoredHash);

            double ratio = input.length == 0
                    ? 0
                    : (double) compressed.length / input.length;

            return new BenchmarkResult(
                    benchmarkCase.name(),
                    compressor.name(),
                    input.length,
                    compressed.length,
                    ratio,
                    restoredCorrectly,
                    originalHash,
                    restoredHash
            );
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read benchmark file: " + benchmarkCase.inputPath(),
                    e
            );
        }
    }
}