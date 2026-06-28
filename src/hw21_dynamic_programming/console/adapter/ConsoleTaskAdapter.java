package hw21_dynamic_programming.console.adapter;

import hw21_dynamic_programming.console.model.InputForm;
import hw21_dynamic_programming.console.model.ResultView;

public interface ConsoleTaskAdapter<I, O> {

    String taskId();

    Class<I> inputType();

    Class<O> resultType();

    InputForm<I> inputForm();

    ResultView<O> resultView();
}
