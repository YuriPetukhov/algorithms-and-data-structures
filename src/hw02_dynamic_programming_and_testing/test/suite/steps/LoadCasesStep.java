package hw02_dynamic_programming_and_testing.test.suite.steps;

import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;
import hw02_dynamic_programming_and_testing.test.suite.TestStep;

public class LoadCasesStep implements TestStep {

    private final FilePairTestSource source;

    public LoadCasesStep(FilePairTestSource source) {
        this.source = source;
    }

    @Override
    public void execute(TestContext ctx) throws Exception {
        ctx.setCases(source.load());
    }
}
