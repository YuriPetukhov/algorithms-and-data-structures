package hw02_dynamic_programming_and_testing.test.strategy;

import java.util.List;

public class PipelineHandler {

    private final List<TestStep> steps;

    public PipelineHandler(List<TestStep> steps) {
        this.steps = steps;
    }

    public void run(TestContext ctx) throws Exception {
        for (TestStep step : steps) {
            step.execute(ctx);
        }
    }
}
