package hw21_dynamic_programming.tasks.spi;

import java.util.List;

@FunctionalInterface
public interface TaskModuleLoader {

    List<TaskModule> load();
}
