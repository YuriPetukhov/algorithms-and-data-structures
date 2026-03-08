package hw08_quick_and_merge_sort.programs.external_sorting.fs;

import java.nio.file.Path;

public record ExternalSortPaths(
        Path workDir,
        Path inputFile,
        Path outputFile
) {}
