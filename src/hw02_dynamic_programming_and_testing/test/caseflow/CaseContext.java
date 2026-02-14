package hw02_dynamic_programming_and_testing.test.caseflow;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.model.TestCase;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

public class CaseContext {

    private final Task task;
    private final TestCase testCase;

    private String inputRaw;
    private String expectedRaw;
    private String actualRaw;

    private String inputNorm;
    private String expectedNorm;
    private String actualNorm;

    private long timeNanos;

    private TestResult result;

    private int benchmarkRuns = 1;

    public CaseContext(Task task, TestCase testCase) {
        this.task = task;
        this.testCase = testCase;
    }

    public Task task() { return task; }
    public TestCase testCase() { return testCase; }

    public String inputRaw() { return inputRaw; }
    public void setInputRaw(String inputRaw) { this.inputRaw = inputRaw; }

    public String expectedRaw() { return expectedRaw; }
    public void setExpectedRaw(String expectedRaw) { this.expectedRaw = expectedRaw; }

    public String actualRaw() { return actualRaw; }
    public void setActualRaw(String actualRaw) { this.actualRaw = actualRaw; }

    public String inputNorm() { return inputNorm; }
    public void setInputNorm(String inputNorm) { this.inputNorm = inputNorm; }

    public String expectedNorm() { return expectedNorm; }
    public void setExpectedNorm(String expectedNorm) { this.expectedNorm = expectedNorm; }

    public String actualNorm() { return actualNorm; }
    public void setActualNorm(String actualNorm) { this.actualNorm = actualNorm; }

    public long timeNanos() { return timeNanos; }
    public void setTimeNanos(long timeNanos) { this.timeNanos = timeNanos; }

    public TestResult result() { return result; }
    public void setResult(TestResult result) { this.result = result; }

    public int benchmarkRuns() { return benchmarkRuns; }
    public void setBenchmarkRuns(int benchmarkRuns) {
        this.benchmarkRuns = Math.max(1, benchmarkRuns);
    }
}
