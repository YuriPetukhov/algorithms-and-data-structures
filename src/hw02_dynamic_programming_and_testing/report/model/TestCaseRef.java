package hw02_dynamic_programming_and_testing.report.model;

import java.nio.file.Path;

public record TestCaseRef(Path inputsDir, String testName, String inputExt) {
}
