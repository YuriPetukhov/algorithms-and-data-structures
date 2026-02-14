package hw02_dynamic_programming_and_testing.report.model;

import hw02_dynamic_programming_and_testing.test.model.TestStatus;

public record Cell(TestStatus status, Long timeNanos, String note) {}
