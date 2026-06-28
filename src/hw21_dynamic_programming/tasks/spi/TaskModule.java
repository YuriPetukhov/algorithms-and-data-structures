package hw21_dynamic_programming.tasks.spi;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

public interface TaskModule {

    Algorithm<?, ?> algorithm();

    TaskDefinition<?, ?> task();
}
