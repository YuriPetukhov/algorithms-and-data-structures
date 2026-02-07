package hw02_dynamic_programming_and_testing.test.caseflow.steps;

import hw02_dynamic_programming_and_testing.test.caseflow.CaseContext;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseStep;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.nio.charset.Charset;
import java.nio.file.Files;

public class LoadExpectedStep implements CaseStep {

    private final Charset charset;

    public LoadExpectedStep(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void execute(CaseContext ctx) throws Exception {
        if (ctx.testCase().expectedFile() == null) {
            ctx.setResult(TestResult.missingExpected(ctx.testCase().name()));
            return;
        }
        String expected = Files.readString(ctx.testCase().expectedFile(), charset);
        ctx.setExpectedRaw(expected);
    }
}