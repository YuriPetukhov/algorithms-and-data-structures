package hw08_quick_and_merge_sort.programs.external_sorting.spi;

import java.nio.file.Path;

public record ExternalSortJob(Path input, Path output, Path workDir, int t) {}
