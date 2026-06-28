package hw21_dynamic_programming.test.adapter;

public interface FileTaskAdapter<I, O> {

    String taskId();

    Class<I> inputType();

    Class<O> resultType();

    I parse(String rawInput);

    String format(O result);
}
