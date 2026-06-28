package hw21_dynamic_programming.tasks.validation;

@FunctionalInterface
public interface ValidationStep<I> {

    void validate(I input);
}
