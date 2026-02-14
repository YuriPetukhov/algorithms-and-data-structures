package hw02_dynamic_programming_and_testing.report.transform;

import hw02_dynamic_programming_and_testing.report.model.TestCaseRef;

public interface RowKeyFormat {
    String id();
    String resolve(TestCaseRef ref) throws Exception;
}
