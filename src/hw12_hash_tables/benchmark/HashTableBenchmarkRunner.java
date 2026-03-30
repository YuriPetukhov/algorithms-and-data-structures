package hw12_hash_tables.benchmark;

import hw12_hash_tables.libs.hashing.IntHashTable;

import java.util.function.Supplier;

public class HashTableBenchmarkRunner {

    public HashTableBenchmarkRow run(
            String name,
            Supplier<IntHashTable> factory,
            int[] insertValues,
            int[] searchValues,
            int[] removeValues
    ) {
        IntHashTable table = factory.get();

        long insertTime = measure(() -> {
            for (int x : insertValues) {
                table.insert(x);
            }
        });

        long searchTime = measure(() -> {
            for (int x : searchValues) {
                table.search(x);
            }
        });

        long removeTime = measure(() -> {
            for (int x : removeValues) {
                table.remove(x);
            }
        });

        return new HashTableBenchmarkRow(
                name,
                insertTime,
                searchTime,
                removeTime
        );
    }

    private long measure(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }
}