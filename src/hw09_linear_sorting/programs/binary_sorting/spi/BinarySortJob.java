package hw09_linear_sorting.programs.binary_sorting.spi;

import java.nio.file.Path;

public record BinarySortJob(
        Path inputFile,
        Path outputFile,
        Path workDir,
        long count
) {
}
