package hw20_rle_file_compression.cli;

import hw20_rle_file_compression.libs.compression.Compressor;

import java.nio.file.Path;

public record Command(
        boolean compress,
        Compressor compressor,
        Path inputPath,
        Path outputPath
) {
}