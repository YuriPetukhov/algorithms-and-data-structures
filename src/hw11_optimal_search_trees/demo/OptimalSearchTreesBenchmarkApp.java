package hw11_optimal_search_trees.demo;

import hw11_optimal_search_trees.benchmark.*;
import hw11_optimal_search_trees.dataset.DatasetToWeightedKeysConverter;
import hw11_optimal_search_trees.libs.searching.trees.optimal.OptimalSearchTreeBuilder;
import hw11_optimal_search_trees.libs.searching.trees.optimal.WeightedKey;
import hw11_optimal_search_trees.libs.searching.trees.optimal.dp.OptimalBSTAlgorithm1;
import hw11_optimal_search_trees.libs.searching.trees.optimal.dp.OptimalBSTAlgorithm2;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class OptimalSearchTreesBenchmarkApp {

    private OptimalSearchTreesBenchmarkApp() {
    }

    public static void main(String[] args) {
        try {
            int[] dataset = buildSyntheticDataset(20_000, 42L);

            List<WeightedKey> keys = DatasetToWeightedKeysConverter.toWeightedKeys(dataset);
            int[] searchKeys = buildSearchKeysFromDataset(dataset, 5_000, 42L);

            List<OptimalSearchTreeBuilder> builders = List.of(
                    new OptimalBSTAlgorithm1(),
                    new OptimalBSTAlgorithm2()
            );

            OptimalSearchBenchmarkRunner runner = new OptimalSearchBenchmarkRunner();
            List<OptimalSearchBenchmarkRow> rows = new ArrayList<>();

            for (OptimalSearchTreeBuilder builder : builders) {
                rows.add(runner.run(builder, keys, searchKeys));
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Path resultsDir = Path.of("tests/results");
            Files.createDirectories(resultsDir);

            Path reportFile = resultsDir.resolve("hw11_optimal_search_trees_report_" + timestamp + ".txt");

            try (PrintStream out = new PrintStream(Files.newOutputStream(reportFile))) {
                OptimalSearchReportPrinter.print(rows, out);
            }

            OptimalSearchReportPrinter.print(rows, System.out);
            System.out.println("Report saved to: " + reportFile.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] buildSyntheticDataset(int n, long seed) {
        Random random = new Random(seed);
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt(5_000);
        }

        return values;
    }

    private static int[] buildSearchKeysFromDataset(int[] dataset, int m, long seed) {
        Random random = new Random(seed);
        int[] result = new int[m];

        for (int i = 0; i < m; i++) {
            result[i] = dataset[random.nextInt(dataset.length)];
        }

        return result;
    }
}