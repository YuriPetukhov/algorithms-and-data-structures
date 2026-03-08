package hw08_quick_and_merge_sort.programs.external_sorting.config;

import java.nio.file.Path;

public record ExternalSortingConfig(
        Path workDir,
        int blockSize
) {}
