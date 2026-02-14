package hw02_dynamic_programming_and_testing.report.mapper;

import hw02_dynamic_programming_and_testing.report.model.Cell;
import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.model.TestStatus;

public class DefaultCellMapper implements CellMapper {

    @Override
    public Cell map(TestResult r) {
        return new Cell(
                r.status(),
                r.timeNanos(),
                r.status() == TestStatus.PASSED ? null : r.message()
        );
    }
}
