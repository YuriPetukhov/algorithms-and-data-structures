package hw02_dynamic_programming_and_testing.report.mapper;

import hw02_dynamic_programming_and_testing.report.model.Cell;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

public interface CellMapper {
    Cell map(TestResult r);
}
