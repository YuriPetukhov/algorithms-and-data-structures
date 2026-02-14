package hw02_dynamic_programming_and_testing.report.config;

import static hw02_dynamic_programming_and_testing.common.props.Props.*;
import hw02_dynamic_programming_and_testing.common.time.TimeUnitParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public record ReportConfig(
        List<Suite> suites
) {
    public record Suite(
            String name,
            Path testDir,
            List<String> taskIds,
            String rowKeyFormatId,
            TimeUnit timeUnit,
            String aggregation,
            Integer runs
    ) {
    }

    public static ReportConfig fromProperties(Properties p) {

        List<Suite> suites = new ArrayList<>();

        String[] suiteNames = get(p, "report.suites", "").split(",");

        for (String rawSuiteName : suiteNames) {
            String name = rawSuiteName.trim();
            if (name.isEmpty()) continue;

            String dir = get(p, "report." + name + ".dir", null);
            String tasksRaw = get(p, "report." + name + ".tasks", "");
            String rowKey = get(p, "report." + name + ".rowkey", rawSuiteName);
            String aggregation = get(p, "report.time.aggregation", "min");

            int runs = getInt(p, "test.benchmark.runs", 1);

            String rawTimeUnit = get(p, "report.time.unit", null);

            TimeUnit timeUnit = TimeUnitParser.tryParse(rawTimeUnit)
                    .orElse(TimeUnit.MILLISECONDS);   // дефолт отчёта

            List<String> tasks = Arrays.stream(tasksRaw.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();

            suites.add(new Suite(
                    name,
                    Path.of(dir),
                    tasks,
                    rowKey,
                    timeUnit,
                    aggregation,
                    runs
            ));
        }

        return new ReportConfig(suites);
    }

}
