package hw21_dynamic_programming.service.bootstrap;

import hw21_dynamic_programming.algorithms.api.Algorithm;
import hw21_dynamic_programming.registry.AlgorithmRegistry;
import hw21_dynamic_programming.registry.InMemoryAlgorithmRegistry;
import hw21_dynamic_programming.registry.InMemoryTaskRegistry;
import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.service.TaskExecutionHandler;
import hw21_dynamic_programming.service.pipeline.DefaultExecutionPipelineProvider;
import hw21_dynamic_programming.service.pipeline.ExecutionPipelineProvider;
import hw21_dynamic_programming.workflow.ExecutionPlan;
import hw21_dynamic_programming.service.steps.CoreStepIds;
import hw21_dynamic_programming.service.steps.ExecuteAlgorithmStep;
import hw21_dynamic_programming.service.steps.StepCatalog;
import hw21_dynamic_programming.service.steps.ValidateInputStep;
import hw21_dynamic_programming.tasks.api.TaskDefinition;
import hw21_dynamic_programming.tasks.spi.ServiceLoaderTaskModuleLoader;
import hw21_dynamic_programming.tasks.spi.TaskModule;
import hw21_dynamic_programming.tasks.spi.TaskModuleLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TaskServiceFactory {

    private final TaskModuleLoader moduleLoader;

    public TaskServiceFactory(TaskModuleLoader moduleLoader) {
        this.moduleLoader = Objects.requireNonNull(
                moduleLoader,
                "Task module loader must not be null."
        );
    }

    public static TaskServiceFactory usingServiceLoader() {
        return new TaskServiceFactory(new ServiceLoaderTaskModuleLoader());
    }

    public TaskServiceRuntime create() {
        List<TaskModule> modules = moduleLoader.load();

        List<Algorithm<?, ?>> algorithms = new ArrayList<>(modules.size());
        List<TaskDefinition<?, ?>> tasks = new ArrayList<>(modules.size());

        for (TaskModule module : modules) {
            algorithms.add(module.algorithm());
            tasks.add(module.task());
        }

        AlgorithmRegistry algorithmRegistry =
                new InMemoryAlgorithmRegistry(algorithms);
        TaskRegistry taskRegistry = new InMemoryTaskRegistry(tasks);

        StepCatalog stepCatalog = new StepCatalog(List.of(
                new ValidateInputStep(taskRegistry),
                new ExecuteAlgorithmStep(taskRegistry, algorithmRegistry)
        ));
        ExecutionPlan defaultPlan = ExecutionPlan.of(
                CoreStepIds.VALIDATE_INPUT,
                CoreStepIds.EXECUTE_ALGORITHM
        );
        ExecutionPipelineProvider pipelineProvider =
                new DefaultExecutionPipelineProvider(
                        taskRegistry,
                        stepCatalog,
                        defaultPlan
                );

        return new TaskServiceRuntime(
                taskRegistry,
                new TaskExecutionHandler(pipelineProvider)
        );
    }
}
