package hw06_sorting_algorithms.programs.sorting.registry;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw06_sorting_algorithms.programs.sorting.spi.SortingVariant;
import hw06_sorting_algorithms.programs.sorting.tasks.ServiceSortingTask;

import java.util.ArrayList;
import java.util.List;

public final class SortingTaskProvider implements ModuleProvider {

    private final SortingVariantRegistry variantRegistry = new SortingVariantRegistry();

    @Override
    public String type() {
        return "sorting";
    }

    @Override
    public List<TaskVariant> variants() {
        List<TaskVariant> taskVariants = new ArrayList<>();

        for (SortingVariant sortingVariant : variantRegistry.list()) {
            taskVariants.add(new TaskVariant(
                    sortingVariant.id(),
                    sortingVariant.displayName()
            ));
        }

        return taskVariants;
    }

    @Override
    public Task task(String variantId) {
        return new ServiceSortingTask(variantId);
    }
}