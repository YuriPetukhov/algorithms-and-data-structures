package hw12_hash_tables.benchmark;

public record HashTableBenchmarkRow(
        String name,
        long insertTimeNs,
        long searchTimeNs,
        long removeTimeNs
) {
}
