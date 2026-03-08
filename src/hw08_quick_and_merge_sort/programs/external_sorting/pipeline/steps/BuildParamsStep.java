package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingParams;

public class BuildParamsStep implements ExternalSortingStep {

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void execute(ExternalSortingContext context) {
        context.setParams(
                new ExternalSortingParams(context.config().blockSize())
        );
    }
}
