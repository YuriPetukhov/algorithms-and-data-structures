package hw21_dynamic_programming.console.controller;

import hw21_dynamic_programming.console.InputFormReader;
import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapter;
import hw21_dynamic_programming.console.adapter.ConsoleTaskAdapterRegistry;
import hw21_dynamic_programming.console.model.ResultView;
import hw21_dynamic_programming.console.presentation.ResultPresenter;
import hw21_dynamic_programming.service.TaskExecutionHandler;

import java.util.Objects;

public final class ConsoleTaskController {

    private final ConsoleTaskAdapterRegistry adapterRegistry;
    private final InputFormReader inputReader;
    private final TaskExecutionHandler executionHandler;
    private final ResultPresenter resultPresenter;

    public ConsoleTaskController(
            ConsoleTaskAdapterRegistry adapterRegistry,
            InputFormReader inputReader,
            TaskExecutionHandler executionHandler,
            ResultPresenter resultPresenter
    ) {
        this.adapterRegistry = Objects.requireNonNull(
                adapterRegistry,
                "Console adapter registry must not be null."
        );
        this.inputReader = Objects.requireNonNull(
                inputReader,
                "Input form reader must not be null."
        );
        this.executionHandler = Objects.requireNonNull(
                executionHandler,
                "Execution handler must not be null."
        );
        this.resultPresenter = Objects.requireNonNull(
                resultPresenter,
                "Result presenter must not be null."
        );
    }

    public void execute(String taskId) {
        ConsoleTaskAdapter<?, ?> adapter = adapterRegistry.getRequired(taskId);
        executeAdapter(adapter);
    }

    private <I, O> void executeAdapter(ConsoleTaskAdapter<I, O> adapter) {
        I input = inputReader.read(adapter.inputForm());
        Object rawResult = executionHandler.handle(adapter.taskId(), input);
        O result = adapter.resultType().cast(rawResult);

        ResultView<O> resultView = adapter.resultView();
        String formattedResult = resultView.formatter().format(result);
        resultPresenter.present(resultView.label(), formattedResult);
    }
}
