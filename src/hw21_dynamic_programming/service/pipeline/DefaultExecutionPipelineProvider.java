package hw21_dynamic_programming.service.pipeline;

import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.workflow.ExecutionContext;
import hw21_dynamic_programming.workflow.ExecutionPlan;
import hw21_dynamic_programming.workflow.ExecutionStep;
import hw21_dynamic_programming.service.steps.StepCatalog;
import hw21_dynamic_programming.tasks.api.TaskDefinition;
import hw21_dynamic_programming.workflow.ExecutionPipeline;

import java.util.List;
import java.util.Objects;

public final class DefaultExecutionPipelineProvider implements ExecutionPipelineProvider {

    private final TaskRegistry taskRegistry;
    private final StepCatalog stepCatalog;
    private final ExecutionPlan defaultPlan;

    public DefaultExecutionPipelineProvider(
            TaskRegistry taskRegistry,
            StepCatalog stepCatalog,
            ExecutionPlan defaultPlan
    ) {
        this.taskRegistry = Objects.requireNonNull(taskRegistry, "Task registry must not be null.");
        this.stepCatalog = Objects.requireNonNull(stepCatalog, "Step catalog must not be null.");
        this.defaultPlan = Objects.requireNonNull(defaultPlan, "Default plan must not be null.");
    }

    @Override
    public ExecutionPipeline create(ExecutionContext context) {
        TaskDefinition<?, ?> task = taskRegistry.getRequired(context.taskId());

        ExecutionPlan plan = task.executionPlan().orElse(defaultPlan);
        List<ExecutionStep> steps = plan.stepIds().stream()
                .map(stepCatalog::getRequired)
                .toList();

        return new ExecutionPipeline(steps);
    }
}
