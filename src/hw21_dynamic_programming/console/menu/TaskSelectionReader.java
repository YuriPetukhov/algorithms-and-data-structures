package hw21_dynamic_programming.console.menu;

import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;

public interface TaskSelectionReader {

    TaskSelection read(List<TaskDefinition<?, ?>> tasks);
}
