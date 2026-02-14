package hw02_dynamic_programming_and_testing.report.model;

public record ReportRow(
        String suite,
        String taskId,
        int total,
        int passed,
        int failed,
        int error,
        long totalTimeNanos,
        long maxTimeNanos
) {}
