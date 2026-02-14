package hw02_dynamic_programming_and_testing.app.core;

public interface MeasurableTask<I, O> extends Task {
    I parse(String input);
    O compute(I input);
    String format(O output);

    @Override
    default String run(String input) {
        return format(compute(parse(input)));
    }
}
