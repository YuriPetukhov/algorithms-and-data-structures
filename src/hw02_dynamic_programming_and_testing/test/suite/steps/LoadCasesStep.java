package hw02_dynamic_programming_and_testing.test.strategy;

import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;

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
