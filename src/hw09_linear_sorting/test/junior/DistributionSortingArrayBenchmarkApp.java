package hw09_linear_sorting.test.junior;

import hw06_sorting_algorithms.libs.sorting.algorithms.SortAlgorithm;
import hw09_linear_sorting.libs.sorting.algorithms.distribution.BucketSortAlgorithm;
import hw09_linear_sorting.libs.sorting.algorithms.distribution.CountingSortAlgorithm;
import hw09_linear_sorting.libs.sorting.algorithms.distribution.RadixSortAlgorithm;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class DistributionSortingArrayBenchmarkApp {

    private static final int RUNS = 5;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 999;

    private DistributionSortingArrayBenchmarkApp() {
    }

    public static void main(String[] args) {
        try {
            List<Integer> sizes = List.of(
                    100,
                    1_000,
                    10_000,
                    100_000,
                    1_000_000
            );

            List<ArrayBenchmarkRow> rows = sizes.stream()
                    .map(DistributionSortingArrayBenchmarkApp::runForSizeUnchecked)
                    .toList();

            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS"));

            Files.createDirectories(Path.of("tests/results"));

            try (PrintStream out = new PrintStream(
                    "tests/results/hw09_array_distribution_report_" + ts + ".txt")) {
                ArrayBenchmarkReportPrinter.print(rows, out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayBenchmarkRow runForSizeUnchecked(int n) {
        try {
            return runForSize(n);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayBenchmarkRow runForSize(int n) {
        System.out.println("========================================");
        System.out.println("N = " + n);

        int[] base = randomArray(n, MIN_VALUE, MAX_VALUE, new Random(42 + n));

        Result counting = benchmark("CountingSort", new CountingSortAlgorithm(), base);
        Result radix = benchmark("RadixSort", new RadixSortAlgorithm(), base);
        Result bucket = benchmark("BucketSort", new BucketSortAlgorithm(), base);

        return new ArrayBenchmarkRow(
                n,
                counting.timeMs(), counting.sorted(),
                radix.timeMs(), radix.sorted(),
                bucket.timeMs(), bucket.sorted()
        );
    }

    private static Result benchmark(String name, SortAlgorithm algorithm, int[] base) {
        long best = Long.MAX_VALUE;
        boolean sorted = true;

        for (int run = 0; run < RUNS; run++) {
            int[] a = Arrays.copyOf(base, base.length);

            long start = System.nanoTime();
            algorithm.sort(a, IntArrayOpsImpl.DEFAULT);
            long timeMs = (System.nanoTime() - start) / 1_000_000;

            if (!isSorted(a)) {
                sorted = false;
            }

            if (timeMs < best) {
                best = timeMs;
            }
        }

        System.out.println(name + ": " + best + " ms | sorted = " + sorted);
        return new Result(best, sorted);
    }

    private static int[] randomArray(int n, int min, int max, Random random) {
        int[] a = new int[n];
        int bound = max - min + 1;

        for (int i = 0; i < n; i++) {
            a[i] = min + random.nextInt(bound);
        }

        return a;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private record Result(long timeMs, boolean sorted) {
    }
}