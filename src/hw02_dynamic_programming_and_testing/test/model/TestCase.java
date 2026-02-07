package hw02_dynamic_programming_and_testing.test.model;

import java.nio.file.Path;

public record TestCase(String name, Path inputFile, Path expectedFile) {
}
