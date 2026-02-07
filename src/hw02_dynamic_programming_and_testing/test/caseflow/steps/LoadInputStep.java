package hw02_dynamic_programming_and_testing.test.caseflow;

import java.nio.charset.Charset;
import java.nio.file.Files;

public class LoadInputStep implements CaseStep {

    private final Charset charset;

    public LoadInputStep(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void execute(CaseContext ctx) throws Exception {
        String input = Files.readString(ctx.testCase().inputFile(), charset);
        ctx.setInputRaw(input);
    }
}
