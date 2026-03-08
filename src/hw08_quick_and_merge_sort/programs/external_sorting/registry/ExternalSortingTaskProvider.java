package hw08_quick_and_merge_sort.programs.external_sorting.registry;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingVariant;
import hw08_quick_and_merge_sort.programs.external_sorting.tasks.ServiceExternalSortingTask;

import java.util.ArrayList;
import java.util.List;

public final class ExternalSortingTaskProvider implements ModuleProvider {

    private final ExternalSortingVariantRegistry registry =
            new ExternalSortingVariantRegistry();

    @Override
    public String type() {
        return "external_sorting";
    }

    @Override
    public List<TaskVariant> variants() {

        List<TaskVariant> variants = new ArrayList<>();

        for (ExternalSortingVariant variant : registry.list()) {
            variants.add(new TaskVariant(
                    variant.id(),
                    variant.displayName()
            ));
        }

        return variants;
    }

    @Override
    public Task task(String variantId) {
        return new ServiceExternalSortingTask(variantId);
    }
}
