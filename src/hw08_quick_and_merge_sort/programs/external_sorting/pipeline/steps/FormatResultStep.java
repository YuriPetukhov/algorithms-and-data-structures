package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;

public class FormatResultStep implements ExternalSortingStep {

    @Override
    public int order() {
        return 6;
    }

    @Override
    public void execute(ExternalSortingContext context) {

        long time = context.runResult().result().timeMillis();

        context.setResult("Time: " + time + " ms");
    }
}
