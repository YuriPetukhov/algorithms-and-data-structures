package hw08_quick_and_merge_sort.programs.external_sorting.pipeline.steps;

import hw08_quick_and_merge_sort.programs.external_sorting.context.ExternalSortingContext;
import hw08_quick_and_merge_sort.programs.external_sorting.fs.WorkDirManager;
import hw08_quick_and_merge_sort.programs.external_sorting.pipeline.ExternalSortingStep;
import hw08_quick_and_merge_sort.programs.external_sorting.registry.ExternalSortingVariantRegistry;
import hw08_quick_and_merge_sort.programs.external_sorting.runner.ExternalSortRunResult;
import hw08_quick_and_merge_sort.programs.external_sorting.runner.ExternalSortingRunner;

public class RunExternalSortingStep implements ExternalSortingStep {

    @Override
    public int order() {
        return 5;
    }

    @Override
    public void execute(ExternalSortingContext context) {

        ExternalSortingVariantRegistry registry =
                new ExternalSortingVariantRegistry();

        WorkDirManager workDirManager =
                new WorkDirManager(context.config().workDir());

        ExternalSortingRunner runner =
                new ExternalSortingRunner(registry, workDirManager);

        try {
            ExternalSortRunResult run =
                    runner.run(
                            context.variantId(),
                            context.params(),
                            context.request()
                    );

            context.setRunResult(run);

        } catch (Exception e) {
            throw new RuntimeException("External programs execution failed", e);
        }
    }
}
