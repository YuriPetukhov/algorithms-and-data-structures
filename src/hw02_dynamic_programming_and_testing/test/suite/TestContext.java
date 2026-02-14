package hw02_dynamic_programming_and_testing.test.suite;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.model.TestCase;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.util.List;

public class TestContext {

    private final Task task;

    private int benchmarkRuns = 1;

    private List<TestCase> cases;
    private List<TestResult> results;

    public TestContext(Task task) {
        this.task = task;
    }

    public Task task() { return task; }

    public int benchmarkRuns() { return benchmarkRuns; }
    public void setBenchmarkRuns(int runs) { this.benchmarkRuns = Math.max(1, runs); }

    public List<TestCase> cases() { return cases; }
    public void setCases(List<TestCase> cases) { this.cases = cases; }

    public List<TestResult> results() { return results; }
    public void setResults(List<TestResult> results) { this.results = results; }
}
