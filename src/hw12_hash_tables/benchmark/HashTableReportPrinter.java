package hw12_hash_tables.benchmark;

import java.io.PrintStream;
import java.util.List;

public final class HashTableReportPrinter {

    private HashTableReportPrinter() {
    }

    public static void print(List<HashTableBenchmarkRow> rows, PrintStream out) {
        out.println("=== Hash Tables Benchmark ===");
        out.println();

        for (HashTableBenchmarkRow row : rows) {
            out.println("Algorithm: " + row.name());
            out.println("  Insert: " + format(row.insertTimeNs()) + " ms");
            out.println("  Search: " + format(row.searchTimeNs()) + " ms");
            out.println("  Remove: " + format(row.removeTimeNs()) + " ms");
            out.println();
        }
    }

    private static String format(long timeNs) {
        return String.format("%.3f", timeNs / 1_000_000.0);
    }
}