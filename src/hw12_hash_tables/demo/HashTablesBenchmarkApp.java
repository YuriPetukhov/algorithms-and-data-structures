package hw12_hash_tables.demo;

import hw12_hash_tables.benchmark.HashTableBenchmarkRow;
import hw12_hash_tables.benchmark.HashTableBenchmarkRunner;
import hw12_hash_tables.benchmark.HashTableReportPrinter;
import hw12_hash_tables.libs.hashing.IntHashTable;
import hw12_hash_tables.libs.hashing.probing.LinearProbing;
import hw12_hash_tables.libs.hashing.probing.QuadraticProbing;
import hw12_hash_tables.libs.hashing.tables.OpenAddressingHashTable;
import hw12_hash_tables.libs.hashing.tables.SeparateChainingHashTable;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public final class HashTablesBenchmarkApp {

    private HashTablesBenchmarkApp() {
    }

    public static void main(String[] args) {
        try {
            int[] insertValues = buildSyntheticDataset(20_000, 42L);
            int[] searchValues = buildSearchKeysFromDataset(insertValues, 5_000, 42L);
            int[] removeValues = buildSearchKeysFromDataset(insertValues, 3_000, 123L);

            List<NamedHashTableFactory> factories = List.of(
                    new NamedHashTableFactory(
                            "Separate chaining",
                            () -> new SeparateChainingHashTable(10_007)
                    ),
                    new NamedHashTableFactory(
                            "Open addressing (linear probing)",
                            () -> new OpenAddressingHashTable(20_011, new LinearProbing())
                    ),
                    new NamedHashTableFactory(
                            "Open addressing (quadratic probing)",
                            () -> new OpenAddressingHashTable(20_011, new QuadraticProbing())
                    )
            );

            HashTableBenchmarkRunner runner = new HashTableBenchmarkRunner();
            List<HashTableBenchmarkRow> rows = new ArrayList<>();

            for (NamedHashTableFactory factory : factories) {
                rows.add(runner.run(
                        factory.name(),
                        factory.supplier(),
                        insertValues,
                        searchValues,
                        removeValues
                ));
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            Path resultsDir = Path.of("tests/results");
            Files.createDirectories(resultsDir);

            Path reportFile = resultsDir.resolve("hw12_hash_tables_report_" + timestamp + ".txt");

            try (PrintStream out = new PrintStream(Files.newOutputStream(reportFile))) {
                HashTableReportPrinter.print(rows, out);
            }

            HashTableReportPrinter.print(rows, System.out);
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

    private record NamedHashTableFactory(
            String name,
            Supplier<IntHashTable> supplier
    ) {
    }
}
