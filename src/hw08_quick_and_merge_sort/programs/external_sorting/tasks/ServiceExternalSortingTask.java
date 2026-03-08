package hw08_quick_and_merge_sort.programs.external_sorting.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStepLoader;

import java.util.List;

public final class ServiceExternalSortingTask
        implements MeasurableTask<String, String> {

    private final String variantId;
    private final ExternalSortingStepLoader stepLoader =
            new ExternalSortingStepLoader();

    public ServiceExternalSortingTask(String variantId) {
        this.variantId = variantId;
    }

    @Override
    public String id() {
        return variantId;
    }

    @Override
    public String displayName() {
        return "External sorting: " + variantId;
    }

    @Override
    public String parse(String input) {
        return input;
    }

    @Override
    public String compute(String input) {

        ExternalSortingContext context =
                new ExternalSortingContext(variantId, input);

        List<ExternalSortingStep> steps = stepLoader.load();

        for (ExternalSortingStep step : steps) {
            step.execute(context);
        }

        return context.result();
    }

    @Override
    public String format(String result) {
        return result;
    }
}