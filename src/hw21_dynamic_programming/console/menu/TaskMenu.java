package hw21_dynamic_programming.console.menu;

import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;

public interface TaskMenu {

    void show(List<TaskDefinition<?, ?>> tasks);
}
