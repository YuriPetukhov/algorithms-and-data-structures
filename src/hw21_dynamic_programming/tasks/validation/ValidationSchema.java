package hw21_dynamic_programming.tasks.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class ValidationSchema<I> {

    private final List<ValidationStep<I>> steps;

    public ValidationSchema(Collection<? extends ValidationStep<I>> steps) {
        Objects.requireNonNull(steps, "Validation steps must not be null.");
        this.steps = List.copyOf(steps);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <I> ValidationSchema<I> of(ValidationStep<I>... steps) {
        Objects.requireNonNull(steps, "Validation steps must not be null.");
        return new ValidationSchema<>(Arrays.asList(steps.clone()));
    }

    public static <I> ValidationSchema<I> empty() {
        return new ValidationSchema<>(List.of());
    }

    public void validate(I input) {
        for (ValidationStep<I> step : steps) {
            step.validate(input);
        }
    }

    public List<ValidationStep<I>> steps() {
        return steps;
    }
}
