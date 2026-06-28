package hw21_dynamic_programming.test.bridge;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.service.TaskExecutionHandler;

import java.util.Objects;

public final class ServiceBackedTask<I, O>
        implements MeasurableTask<I, O> {

    private final FileTaskAdapter<I, O> adapter;
    private final TaskExecutionHandler executionHandler;

    public ServiceBackedTask(
            FileTaskAdapter<I, O> adapter,
            TaskExecutionHandler executionHandler
    ) {
        this.adapter = Objects.requireNonNull(adapter, "Adapter must not be null.");
        this.executionHandler = Objects.requireNonNull(
                executionHandler,
                "Execution handler must not be null."
        );
    }

    @Override
    public I parse(String rawInput) {
        return adapter.parse(rawInput);
    }

    @Override
    public O compute(I input) {
        Object result = executionHandler.handle(adapter.taskId(), input);
        return adapter.resultType().cast(result);
    }

    @Override
    public String format(O result) {
        return adapter.format(result);
    }
}
