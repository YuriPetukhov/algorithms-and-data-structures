package hw21_dynamic_programming.test.model;

import java.nio.file.Path;

public record FileTestCase(
        String name,
        Path inputFile,
        Path expectedFile
) {
}
