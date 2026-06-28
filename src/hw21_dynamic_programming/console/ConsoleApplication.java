package hw21_dynamic_programming.console;

import hw21_dynamic_programming.console.controller.ConsoleTaskController;
import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.console.io.EndOfInputException;
import hw21_dynamic_programming.console.menu.TaskMenu;
import hw21_dynamic_programming.console.menu.TaskSelection;
import hw21_dynamic_programming.console.menu.TaskSelectionReader;
import hw21_dynamic_programming.console.presentation.ErrorPresenter;
import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;
import java.util.Objects;

public final class ConsoleApplication {

    private final TaskRegistry taskRegistry;
    private final TaskMenu taskMenu;
    private final TaskSelectionReader selectionReader;
    private final ConsoleTaskController taskController;
    private final ErrorPresenter errorPresenter;
    private final ConsoleOutput output;

    public ConsoleApplication(
            TaskRegistry taskRegistry,
            TaskMenu taskMenu,
            TaskSelectionReader selectionReader,
            ConsoleTaskController taskController,
            ErrorPresenter errorPresenter,
            ConsoleOutput output
    ) {
        this.taskRegistry = Objects.requireNonNull(
                taskRegistry,
                "Task registry must not be null."
        );
        this.taskMenu = Objects.requireNonNull(taskMenu, "Task menu must not be null.");
        this.selectionReader = Objects.requireNonNull(
                selectionReader,
                "Task selection reader must not be null."
        );
        this.taskController = Objects.requireNonNull(
                taskController,
                "Task controller must not be null."
        );
        this.errorPresenter = Objects.requireNonNull(
                errorPresenter,
                "Error presenter must not be null."
        );
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
    }

    public void run() {
        while (true) {
            try {
                List<TaskDefinition<?, ?>> tasks = taskRegistry.getAll();
                taskMenu.show(tasks);

                TaskSelection selection = selectionReader.read(tasks);
                if (selection.exitRequested()) {
                    return;
                }

                taskController.execute(selection.taskId());
                output.println();
            } catch (EndOfInputException exception) {
                return;
            } catch (RuntimeException exception) {
                errorPresenter.present(exception);
                output.println();
            }
        }
    }
}
