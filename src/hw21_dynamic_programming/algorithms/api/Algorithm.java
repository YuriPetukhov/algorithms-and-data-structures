package hw21_dynamic_programming.algorithms.api;

public interface Algorithm<I, O> {

    String id();

    Class<I> inputType();

    Class<O> resultType();

    O execute(I input);
}
