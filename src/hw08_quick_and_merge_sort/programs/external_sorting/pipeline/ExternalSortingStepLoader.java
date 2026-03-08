package hw08_quick_and_merge_sort.programs.external_sorting.pipeline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

public final class ExternalSortingStepLoader {

    public List<ExternalSortingStep> load() {
        List<ExternalSortingStep> steps = new ArrayList<>();

        ServiceLoader<ExternalSortingStep> loader =
                ServiceLoader.load(ExternalSortingStep.class);

        for (ExternalSortingStep step : loader) {
            steps.add(step);
        }

        steps.sort(Comparator.comparingInt(ExternalSortingStep::order));
        return steps;
    }
}