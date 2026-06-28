package hw21_dynamic_programming.service.steps;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.registry.AlgorithmRegistry;
import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.tasks.api.TaskDefinition;
import hw21_dynamic_programming.workflow.ExecutionContext;
import hw21_dynamic_programming.workflow.ExecutionStep;

public final class ExecuteAlgorithmStep implements ExecutionStep {

    private final TaskRegistry taskRegistry;
    private final AlgorithmRegistry algorithmRegistry;

    public ExecuteAlgorithmStep(
            TaskRegistry taskRegistry,
            AlgorithmRegistry algorithmRegistry
    ) {
        this.taskRegistry = taskRegistry;
        this.algorithmRegistry = algorithmRegistry;
    }

    @Override
    public String id() {
        return CoreStepIds.EXECUTE_ALGORITHM;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void execute(ExecutionContext context) {
        TaskDefinition task =
                taskRegistry.getRequired(context.taskId());

        Algorithm algorithm =
                algorithmRegistry.getRequired(task.algorithmId());

        Object result =
                algorithm.execute(context.payload());

        context.setResult(result);
    }
}
