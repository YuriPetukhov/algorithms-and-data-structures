package hw20_rle_file_compression.benchmark;

import java.nio.file.Path;

public record BenchmarkCase(
        String name,
        Path inputPath
) {
}