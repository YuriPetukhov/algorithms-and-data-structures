package hw08_quick_and_merge_sort.programs.external_sorting.runner;

import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortResult;

import java.nio.file.Path;

public record ExternalSortRunResult(
        Path workDir,
        ExternalSortResult result
) {}