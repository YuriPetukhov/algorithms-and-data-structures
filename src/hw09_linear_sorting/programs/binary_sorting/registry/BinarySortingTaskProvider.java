package hw09_linear_sorting.programs.binary_sorting.registry;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw09_linear_sorting.programs.binary_sorting.variants.BinarySortingVariant;
import hw09_linear_sorting.programs.binary_sorting.tasks.ServiceBinarySortingTask;

import java.util.ArrayList;
import java.util.List;

public final class BinarySortingTaskProvider implements ModuleProvider {

    private final BinarySortingVariantRegistry variantRegistry =
            new BinarySortingVariantRegistry();

    @Override
    public String type() {
        return "binary_sorting_task";
    }

    @Override
    public List<TaskVariant> variants() {
        List<TaskVariant> taskVariants = new ArrayList<>();

        for (BinarySortingVariant variant : variantRegistry.list()) {
            taskVariants.add(new TaskVariant(
                    variant.id(),
                    variant.displayName()
            ));
        }

        return taskVariants;
    }

    @Override
    public Task task(String variantId) {
        return new ServiceBinarySortingTask(variantId);
    }
}