package hw21_dynamic_programming.test.bridge;

public interface MeasurableTask<I, O> {

    I parse(String rawInput);

    O compute(I input);

    String format(O result);
}
