package hw21_dynamic_programming.tasks.api;

import hw21_dynamic_programming.workflow.ExecutionPlan;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

import java.util.Optional;

public interface TaskDefinition<I, O> {

    String id();

    String name();

    String algorithmId();

    Class<I> inputType();

    Class<O> resultType();

    ValidationSchema<I> validationSchema();

    Optional<ExecutionPlan> executionPlan();

    default void validateInput(Object input) {
        if (!inputType().isInstance(input)) {
            String actual = input == null ? "null" : input.getClass().getName();
            throw new IllegalArgumentException(
                    "Task '%s' expects %s, but got %s"
                            .formatted(id(), inputType().getName(), actual)
            );
        }

        validationSchema().validate(inputType().cast(input));
    }
}
