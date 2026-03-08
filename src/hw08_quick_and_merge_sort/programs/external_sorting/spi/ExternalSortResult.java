package hw08_quick_and_merge_sort.programs.external_sorting.spi;

import java.nio.file.Path;

public record ExternalSortResult(
        long timeMillis,
        Path outputFile
) {}
