package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.input.ExternalSortingRequest;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;

public class ParseInputStep implements ExternalSortingStep {
    @Override
    public int order() {
        return 2;
    }

    @Override
    public void execute(ExternalSortingContext context) {

        if (context.rawInput() == null) {
            throw new IllegalArgumentException("Input is null");
        }

        String[] parts = context.rawInput().trim().split("\\s+");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Expected: <N> <T>");
        }

        int n;
        int t;

        try {
            n = Integer.parseInt(parts[0]);
            t = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integers: <N> <T>");
        }

        context.setRequest(new ExternalSortingRequest(n, t));
    }
}
