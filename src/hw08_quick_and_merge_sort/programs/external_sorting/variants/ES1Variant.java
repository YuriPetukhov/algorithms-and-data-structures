package hw08_quick_and_merge_sort.programs.external_sorting.variants;

import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.ExternalSortES1;
import hw08_quick_and_merge_sort.programs.external_sorting.solver.ExternalSortSolver;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortResult;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingParams;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingVariant;

import java.nio.file.Path;

public final class ES1Variant implements ExternalSortingVariant {

    @Override
    public String id() {
        return "es1";
    }

    @Override
    public String displayName() {
        return "External Sort ES1 (Buckets)";
    }

    @Override
    public ExternalSortSolver build(ExternalSortingParams params) {

        return job -> {

            long start = System.currentTimeMillis();

            int t = job.t();
            Path[] buckets = new Path[t + 1];

            for (int i = 1; i <= t; i++) {
                buckets[i] = job.workDir()
                        .resolve("bucket_" + i + ".txt");
            }

            ExternalSortES1.sort(
                    job.input(),
                    job.output(),
                    t,
                    buckets
            );

            long end = System.currentTimeMillis();

            return new ExternalSortResult(
                    end - start,
                    job.output()
            );
        };
    }
}
