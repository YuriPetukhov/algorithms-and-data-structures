package hw02_dynamic_programming_and_testing.report.transform;

import hw02_dynamic_programming_and_testing.report.mapper.CellMapper;
import hw02_dynamic_programming_and_testing.report.model.Cell;
import hw02_dynamic_programming_and_testing.report.model.ReportTable;
import hw02_dynamic_programming_and_testing.report.model.TestCaseRef;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ReportTableBuilder {

    public ReportTable build(
            String suiteName,
            Path inputsDir,
            String inputExt,
            List<String> taskOrder,
            Map<String, List<TestResult>> resultsByTask,
            RowKeyResolver rowKeyResolver,
            CellMapper cellMapper,
            int runs,
            TimeUnit timeUnit,
            String aggregation
    ) throws Exception {

        Map<String, Map<String, Cell>> rows = new LinkedHashMap<>();


        for (String taskId : taskOrder) {
            List<TestResult> list = resultsByTask.getOrDefault(taskId, List.of());

            for (TestResult r : list) {
                String testName = r.testName();

                String rowKey = rowKeyResolver.resolve(
                        new TestCaseRef(inputsDir, testName, inputExt)
                );

                rows.computeIfAbsent(rowKey, k -> new LinkedHashMap<>())
                        .put(taskId, cellMapper.map(r));
            }
        }

        return new ReportTable(
                suiteName,
                rows,
                taskOrder,
                runs,
                timeUnit,
                aggregation
        );
    }
}