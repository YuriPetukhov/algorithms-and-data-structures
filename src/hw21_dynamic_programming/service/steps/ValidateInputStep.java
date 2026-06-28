package hw21_dynamic_programming.service.steps;

import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.tasks.api.TaskDefinition;
import hw21_dynamic_programming.workflow.ExecutionContext;
import hw21_dynamic_programming.workflow.ExecutionStep;

public final class ValidateInputStep implements ExecutionStep {

    private final TaskRegistry taskRegistry;

    public ValidateInputStep(TaskRegistry taskRegistry) {
        this.taskRegistry = taskRegistry;
    }

    @Override
    public String id() {
        return CoreStepIds.VALIDATE_INPUT;
    }

    @Override
    public void execute(ExecutionContext context) {
        TaskDefinition<?, ?> task =
                taskRegistry.getRequired(context.taskId());

        validate(task, context.payload());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void validate(
            TaskDefinition task,
            Object input
    ) {
        task.validationSchema().validate(input);
    }
}