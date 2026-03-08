package hw08_quick_and_merge_sort.test.report;

import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.util.List;

public interface TestReporter {

    void print(List<TestResult> results);

    void printFatal(Exception e);
}
