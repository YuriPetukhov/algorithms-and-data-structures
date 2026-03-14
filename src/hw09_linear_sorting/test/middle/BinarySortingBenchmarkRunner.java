package hw09_linear_sorting.test.middle;

import hw09_linear_sorting.programs.binary_sorting.io.UInt16RandomBinaryFileGenerator;
import hw09_linear_sorting.programs.binary_sorting.solver.BinaryBucketSortSolver;
import hw09_linear_sorting.programs.binary_sorting.solver.BinaryCountingSortSolver;
import hw09_linear_sorting.programs.binary_sorting.solver.BinaryRadixSortSolver;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortJob;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortResult;
import hw09_linear_sorting.programs.binary_sorting.spi.BinarySortingParams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class BinarySortingBenchmarkRunner {

    private static final int BUFFER_SIZE = 1_000_000;

    private BinarySortingBenchmarkRunner() {
    }

    public static List<BinarySortingRow> runAll() throws Exception {
        List<Long> sizes = List.of(
                1_000L,
                10_000L,
                100_000L,
                1_000_000L,
                10_000_000L,
                1_000_000_000L
        );

        List<BinarySortingRow> rows = new ArrayList<>();

        for (long n : sizes) {
            System.out.println("========================================");
            System.out.println("N = " + n);

            Path dir = Path.of("tests/hw09/" + n);
            Files.createDirectories(dir);

            Path input = dir.resolve("input.bin");
            Path output = dir.resolve("output.bin");
            Path work = dir.resolve("work");
            Files.createDirectories(work);

            if (!Files.exists(input)) {
                UInt16RandomBinaryFileGenerator.generate(input, n);
            }

            BinarySortingRunResult counting = runCounting(input, output, work, n);
            BinarySortingRunResult radix = runRadix(input, output, work, n);
            BinarySortingRunResult bucket = runBucket(input, output, work, n);

            rows.add(new BinarySortingRow(
                    n,
                    counting.timeMs(), counting.sorted(),
                    radix.timeMs(), radix.sorted(),
                    bucket.timeMs(), bucket.sorted()
            ));
        }

        return rows;
    }

    private static BinarySortingRunResult runCounting(Path input, Path output, Path work, long count) throws Exception {
        BinarySortingParams params = new BinarySortingParams(BUFFER_SIZE);
        BinaryCountingSortSolver solver = new BinaryCountingSortSolver(params);

        BinarySortResult result = solver.solve(new BinarySortJob(input, output, work, count));
        boolean sorted = BinarySortValidator.isSorted(output);

        System.out.println("CountingSort: " + result.timeMillis() + " ms | sorted = " + sorted);
        return new BinarySortingRunResult(result.timeMillis(), sorted);
    }

    private static BinarySortingRunResult runRadix(Path input, Path output, Path work, long count) throws Exception {
        BinarySortingParams params = new BinarySortingParams(BUFFER_SIZE);
        BinaryRadixSortSolver solver = new BinaryRadixSortSolver(params);

        BinarySortResult result = solver.solve(new BinarySortJob(input, output, work, count));
        boolean sorted = BinarySortValidator.isSorted(output);

        System.out.println("RadixSort:    " + result.timeMillis() + " ms | sorted = " + sorted);
        return new BinarySortingRunResult(result.timeMillis(), sorted);
    }

    private static BinarySortingRunResult runBucket(Path input, Path output, Path work, long count) throws Exception {
        BinarySortingParams params = new BinarySortingParams(BUFFER_SIZE);
        BinaryBucketSortSolver solver = new BinaryBucketSortSolver(params);

        BinarySortResult result = solver.solve(new BinarySortJob(input, output, work, count));
        boolean sorted = BinarySortValidator.isSorted(output);

        System.out.println("BucketSort:   " + result.timeMillis() + " ms | sorted = " + sorted);
        return new BinarySortingRunResult(result.timeMillis(), sorted);
    }
}