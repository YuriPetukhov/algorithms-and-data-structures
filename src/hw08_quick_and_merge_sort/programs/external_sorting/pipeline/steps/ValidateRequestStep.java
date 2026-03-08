package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;

public class ValidateRequestStep implements ExternalSortingStep {
    @Override
    public int order() {
        return 3;
    }

    @Override
    public void execute(ExternalSortingContext context) {

        if (context.request().n() <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }

        if (context.request().t() <= 0) {
            throw new IllegalArgumentException("T must be > 0");
        }
    }
}
