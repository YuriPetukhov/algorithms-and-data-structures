package hw21_dynamic_programming.test.caseflow.steps;

import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CaseStep;
import hw21_dynamic_programming.test.model.TestResult;

import java.nio.charset.Charset;
import java.nio.file.Files;

public final class LoadExpectedStep implements CaseStep {

    private final Charset charset;

    public LoadExpectedStep(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void execute(CaseContext context) throws Exception {
        if (context.testCase().expectedFile() == null) {
            context.result(TestResult.missingExpected(context.testCase().name()));
            return;
        }
        context.expectedRaw(Files.readString(context.testCase().expectedFile(), charset));
    }
}
