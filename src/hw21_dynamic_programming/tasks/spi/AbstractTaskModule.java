package hw21_dynamic_programming.tasks.spi;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.Objects;

public abstract class AbstractTaskModule<I, O> implements TaskModule {

    private final Algorithm<I, O> algorithm;
    private final TaskDefinition<I, O> task;

    protected AbstractTaskModule(
            Algorithm<I, O> algorithm,
            TaskDefinition<I, O> task
    ) {
        this.algorithm = Objects.requireNonNull(
                algorithm,
                "Algorithm must not be null."
        );
        this.task = Objects.requireNonNull(
                task,
                "Task must not be null."
        );
        verifyCompatibility();
    }

    @Override
    public final Algorithm<I, O> algorithm() {
        return algorithm;
    }

    @Override
    public final TaskDefinition<I, O> task() {
        return task;
    }

    private void verifyCompatibility() {
        if (!task.algorithmId().equals(algorithm.id())) {
            throw new IllegalArgumentException(
                    "Task '%s' refers to algorithm '%s', but module provides '%s'."
                            .formatted(task.id(), task.algorithmId(), algorithm.id())
            );
        }
        if (!task.inputType().equals(algorithm.inputType())) {
            throw new IllegalArgumentException(
                    "Input type mismatch in task module: " + task.id()
            );
        }
        if (!task.resultType().equals(algorithm.resultType())) {
            throw new IllegalArgumentException(
                    "Result type mismatch in task module: " + task.id()
            );
        }
    }
}
