package hw02_dynamic_programming_and_testing.report.model;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public record ReportTable(
        String suiteName,
        Map<String, Map<String, Cell>> rows,
        List<String> columnOrder,
        int runs,
        TimeUnit displayTimeUnit,
        String aggregation
) {}
