package hw08_quick_and_merge_sort.programs.external_sorting.runner;

import hw08_quick_and_merge_sort.programs.external_sorting.fs.ExternalSortPaths;
import hw08_quick_and_merge_sort.programs.external_sorting.fs.WorkDirManager;
import hw08_quick_and_merge_sort.programs.external_sorting.input.ExternalSortingRequest;
import hw08_quick_and_merge_sort.programs.external_sorting.registry.ExternalSortingVariantRegistry;
import hw08_quick_and_merge_sort.programs.external_sorting.solver.ExternalSortSolver;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.*;
import hw08_quick_and_merge_sort.programs.external_sorting.util.RandomIntFileGenerator;

public final class ExternalSortingRunner {

    private final ExternalSortingVariantRegistry registry;
    private final WorkDirManager workDirManager;

    public ExternalSortingRunner(ExternalSortingVariantRegistry registry,
                                 WorkDirManager workDirManager) {
        this.registry = registry;
        this.workDirManager = workDirManager;
    }

    public ExternalSortRunResult run(String variantId,
                                     ExternalSortingParams params,
                                     ExternalSortingRequest request) throws Exception {

        ExternalSortingVariant variant = registry
                .find(variantId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown variant: " + variantId));

        ExternalSortSolver solver = variant.build(params);

        ExternalSortPaths paths = workDirManager.createRun();

        RandomIntFileGenerator.generate(
                paths.inputFile(),
                request.n(),
                request.t()
        );

        ExternalSortJob job = new ExternalSortJob(
                paths.inputFile(),
                paths.outputFile(),
                paths.workDir(),
                request.t()
        );

        ExternalSortResult result = solver.solve(job);

        return new ExternalSortRunResult(
                paths.workDir(),
                result
        );
    }
}