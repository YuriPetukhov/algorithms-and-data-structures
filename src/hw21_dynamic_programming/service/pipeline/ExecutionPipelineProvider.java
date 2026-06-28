package hw21_dynamic_programming.service.pipeline;

import hw21_dynamic_programming.workflow.ExecutionContext;
import hw21_dynamic_programming.workflow.ExecutionPipeline;

public interface ExecutionPipelineProvider {

    ExecutionPipeline create(ExecutionContext context);
}
