package hw21_dynamic_programming.workflow;

import java.util.List;
import java.util.Objects;

public final class ExecutionPipeline {

    private final List<ExecutionStep> steps;

    public ExecutionPipeline(List<? extends ExecutionStep> steps) {
        Objects.requireNonNull(steps, "Pipeline steps must not be null.");
        this.steps = List.copyOf(steps);
    }

    public void execute(ExecutionContext context) {
        for (ExecutionStep step : steps) {
            step.execute(context);
        }
    }
}
