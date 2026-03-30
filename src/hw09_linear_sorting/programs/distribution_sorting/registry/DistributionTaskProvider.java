package hw09_linear_sorting.programs.distribution_sorting.registry;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw09_linear_sorting.programs.distribution_sorting.spi.DistributionSortingVariant;
import hw09_linear_sorting.programs.distribution_sorting.tasks.ServiceDistributionSortingTask;

import java.util.ArrayList;
import java.util.List;

public final class DistributionTaskProvider implements ModuleProvider {

    private final DistributionVariantRegistry variantRegistry = new DistributionVariantRegistry();

    @Override
    public String type() {
        return "distribution_sorting_task";
    }

    @Override
    public List<TaskVariant> variants() {
        List<TaskVariant> taskVariants = new ArrayList<>();

        for (DistributionSortingVariant variant : variantRegistry.list()) {
            taskVariants.add(new TaskVariant(
                    variant.id(),
                    variant.displayName()
            ));
        }

        return taskVariants;
    }

    @Override
    public Task task(String variantId) {
        return new ServiceDistributionSortingTask(variantId);
    }
}