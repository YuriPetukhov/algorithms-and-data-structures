package hw08_quick_and_merge_sort.programs.external_sorting.variants;

import hw08_quick_and_merge_sort.libs.sorting.algorithms.external.ExternalSortES2;
import hw08_quick_and_merge_sort.programs.external_sorting.solver.ExternalSortSolver;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortResult;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingParams;
import hw08_quick_and_merge_sort.programs.external_sorting.spi.ExternalSortingVariant;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class ES2Variant implements ExternalSortingVariant {

    @Override
    public String id() {
        return "es2";
    }

    @Override
    public String displayName() {
        return "External Sort ES2 (Natural Merge)";
    }

    @Override
    public ExternalSortSolver build(ExternalSortingParams params) {

        return job -> {

            long start = System.currentTimeMillis();

            Path f1 = job.workDir().resolve("f1.txt");
            Path f2 = job.workDir().resolve("f2.txt");
            Path temp = job.workDir().resolve("temp.txt");

            Path current = job.input();

            while (true) {

                int runs = ExternalSortES2.splitToRuns(current, f1, f2);

                if (runs <= 1) {
                    Files.copy(current,
                            job.output(),
                            StandardCopyOption.REPLACE_EXISTING);
                    break;
                }

                ExternalSortES2.mergeRuns(f1, f2, temp);

                Path swap = current;
                current = temp;
                temp = swap;
            }

            long end = System.currentTimeMillis();

            return new ExternalSortResult(
                    end - start,
                    job.output()
            );
        };
    }
}
