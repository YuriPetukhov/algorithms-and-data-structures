package hw21_dynamic_programming.tasks.api;

import hw21_dynamic_programming.workflow.ExecutionPlan;
import hw21_dynamic_programming.tasks.validation.ValidationSchema;

import java.util.Objects;
import java.util.Optional;

public record SimpleTaskDefinition<I, O>(
        String id,
        String name,
        String algorithmId,
        Class<I> inputType,
        Class<O> resultType,
        ValidationSchema<I> validationSchema,
        Optional<ExecutionPlan> executionPlan
) implements TaskDefinition<I, O> {

    public SimpleTaskDefinition {
        requireText(id, "Task id");
        requireText(name, "Task name");
        requireText(algorithmId, "Algorithm id");
        Objects.requireNonNull(inputType, "Task input type must not be null.");
        Objects.requireNonNull(resultType, "Task result type must not be null.");
        Objects.requireNonNull(validationSchema, "Validation schema must not be null.");
        Objects.requireNonNull(executionPlan, "Execution plan option must not be null.");
    }

    public static <I, O> SimpleTaskDefinition<I, O> usingDefaultPipeline(
            String id,
            String name,
            String algorithmId,
            Class<I> inputType,
            Class<O> resultType,
            ValidationSchema<I> validationSchema
    ) {
        return new SimpleTaskDefinition<>(
                id,
                name,
                algorithmId,
                inputType,
                resultType,
                validationSchema,
                Optional.empty()
        );
    }

    public static <I, O> SimpleTaskDefinition<I, O> usingPlan(
            String id,
            String name,
            String algorithmId,
            Class<I> inputType,
            Class<O> resultType,
            ValidationSchema<I> validationSchema,
            ExecutionPlan plan
    ) {
        return new SimpleTaskDefinition<>(
                id,
                name,
                algorithmId,
                inputType,
                resultType,
                validationSchema,
                Optional.of(Objects.requireNonNull(plan, "Plan must not be null."))
        );
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank.");
        }
    }
}
