package hw21_dynamic_programming.console.menu;

import hw21_dynamic_programming.console.io.ConsoleOutput;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;
import java.util.Objects;

public final class StandardTaskMenu implements TaskMenu {

    private final ConsoleOutput output;

    public StandardTaskMenu(ConsoleOutput output) {
        this.output = Objects.requireNonNull(output, "Console output must not be null.");
    }

    @Override
    public void show(List<TaskDefinition<?, ?>> tasks) {
        Objects.requireNonNull(tasks, "Tasks must not be null.");
        output.println("Выберите задачу:");
        for (int index = 0; index < tasks.size(); index++) {
            TaskDefinition<?, ?> task = tasks.get(index);
            output.println(
                    "%d. %s [%s]"
                            .formatted(index + 1, task.name(), task.id())
            );
        }
        output.println("0. Выход");
    }
}
