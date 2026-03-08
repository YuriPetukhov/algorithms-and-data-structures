package hw08_quick_and_merge_sort.programs.external_sorting.benchmark;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw06_sorting_algorithms.common.module.ModuleProvider;
import hw06_sorting_algorithms.common.module.ModuleRegistry;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.*;

public final class ExternalSortingBenchmarkApp {

    private static final int[] N_VALUES = {
            100,
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    public static void main(String[] args) {

        try {
            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Files.createDirectories(Path.of("tests/results"));

            System.setOut(new PrintStream(
                    "tests/results/external_sort_report_" + ts + ".txt"
            ));

            ModuleRegistry registry = new ModuleRegistry();

            Optional<ModuleProvider> providerOpt =
                    registry.find("external_sorting");

            if (providerOpt.isEmpty()) {
                System.out.println("external_sorting module not found.");
                return;
            }

            ModuleProvider provider = providerOpt.get();

            System.out.println("External Sorting Benchmark");
            System.out.println();
            System.out.println("Timeout per run: 2 minutes");
            System.out.println();

            System.out.printf(
                    "%-10s %-10s %-15s %-15s %-15s%n",
                    "N", "T", "ES1(ms)", "ES2(ms)", "ES3(ms)"
            );

            for (int n : N_VALUES) {
                int[] tValues = {10, n};

                for (int t : tValues) {
                    String es1 = run(provider, "es1", n, t);
                    String es2 = run(provider, "es2", n, t);
                    String es3 = run(provider, "es3", n, t);

                    System.out.printf(
                            "%-10d %-10d %-15s %-15s %-15s%n",
                            n, t, es1, es2, es3
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String run(ModuleProvider provider,
                              String variantId,
                              int n,
                              int t) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<String> future = executor.submit(() -> {
                Task task = provider.task(variantId);

                String input = n + " " + t;
                String result = task.run(input);

                String[] parts = result.split("\\s+");
                return parts[1];
            });

            return future.get(2, TimeUnit.MINUTES);

        } catch (TimeoutException e) {
            return "TIMEOUT";

        } catch (ExecutionException e) {
            Throwable cause = e.getCause();

            if (cause instanceof OutOfMemoryError) {
                return "OUT_OF_MEMORY";
            }

            if (cause != null && cause.getCause() instanceof OutOfMemoryError) {
                return "OUT_OF_MEMORY";
            }

            return "ERROR";

        } catch (Throwable e) {
            if (e instanceof OutOfMemoryError) {
                return "OUT_OF_MEMORY";
            }
            return "ERROR";

        } finally {
            executor.shutdownNow();
        }
    }
}