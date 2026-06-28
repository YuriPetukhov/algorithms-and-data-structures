package hw21_dynamic_programming.service;

import hw21_dynamic_programming.workflow.ExecutionContext;
import hw21_dynamic_programming.workflow.ExecutionPipeline;
import hw21_dynamic_programming.service.pipeline.ExecutionPipelineProvider;

import java.util.Objects;

public final class TaskExecutionHandler {

    private final ExecutionPipelineProvider pipelineProvider;

    public TaskExecutionHandler(ExecutionPipelineProvider pipelineProvider) {
        this.pipelineProvider = Objects.requireNonNull(
                pipelineProvider,
                "Pipeline provider must not be null."
        );
    }

    public Object handle(String taskId, Object input) {
        ExecutionContext context = new ExecutionContext(taskId, input);
        ExecutionPipeline pipeline = pipelineProvider.create(context);
        pipeline.execute(context);
        return context.result();
    }
}
