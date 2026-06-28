package hw21_dynamic_programming.test.caseflow.steps;

import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CaseStep;

import java.nio.charset.Charset;
import java.nio.file.Files;

public final class LoadInputStep implements CaseStep {

    private final Charset charset;

    public LoadInputStep(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void execute(CaseContext context) throws Exception {
        context.inputRaw(Files.readString(context.testCase().inputFile(), charset));
    }
}
