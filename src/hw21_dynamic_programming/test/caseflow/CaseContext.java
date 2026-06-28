package hw21_dynamic_programming.test.caseflow;

import hw21_dynamic_programming.test.bridge.MeasurableTask;
import hw21_dynamic_programming.test.model.FileTestCase;
import hw21_dynamic_programming.test.model.TestResult;

public final class CaseContext {

    private final MeasurableTask<?, ?> task;
    private final FileTestCase testCase;
    private final int benchmarkRuns;
    private final boolean timeoutEnabled;
    private final long timeoutMillis;

    private String inputRaw;
    private String expectedRaw;
    private String actualRaw;
    private String expectedNormalized;
    private String actualNormalized;
    private long timeNanos;
    private TestResult result;

    public CaseContext(
            MeasurableTask<?, ?> task,
            FileTestCase testCase,
            int benchmarkRuns,
            boolean timeoutEnabled,
            long timeoutMillis
    ) {
        this.task = task;
        this.testCase = testCase;
        this.benchmarkRuns = benchmarkRuns;
        this.timeoutEnabled = timeoutEnabled;
        this.timeoutMillis = timeoutMillis;
    }

    public MeasurableTask<?, ?> task() { return task; }
    public FileTestCase testCase() { return testCase; }
    public int benchmarkRuns() { return benchmarkRuns; }
    public boolean timeoutEnabled() { return timeoutEnabled; }
    public long timeoutMillis() { return timeoutMillis; }
    public String inputRaw() { return inputRaw; }
    public void inputRaw(String value) { inputRaw = value; }
    public String expectedRaw() { return expectedRaw; }
    public void expectedRaw(String value) { expectedRaw = value; }
    public String actualRaw() { return actualRaw; }
    public void actualRaw(String value) { actualRaw = value; }
    public String expectedNormalized() { return expectedNormalized; }
    public void expectedNormalized(String value) { expectedNormalized = value; }
    public String actualNormalized() { return actualNormalized; }
    public void actualNormalized(String value) { actualNormalized = value; }
    public long timeNanos() { return timeNanos; }
    public void timeNanos(long value) { timeNanos = value; }
    public TestResult result() { return result; }
    public void result(TestResult value) { result = value; }
}
