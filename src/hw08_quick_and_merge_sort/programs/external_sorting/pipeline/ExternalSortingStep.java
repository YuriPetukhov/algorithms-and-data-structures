package hw08_quick_and_merge_sort.programs.external_sorting.pipeline;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;

public interface ExternalSortingStep {
    int order();
    void execute(ExternalSortingContext context);
}
