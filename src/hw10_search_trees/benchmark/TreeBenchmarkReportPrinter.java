package hw10_search_trees.benchmark;

import java.io.PrintStream;
import java.util.List;

public final class TreeBenchmarkReportPrinter {

    private TreeBenchmarkReportPrinter() {
    }

    public static void print(List<TreeBenchmarkRow> rows, PrintStream out) {
        out.println();
        out.println("==================== SEARCH TREES BENCHMARK REPORT ====================");
        out.printf("%18s | %12s | %10s | %12s | %12s | %12s | %10s%n",
                "Tree", "InputOrder", "N", "Insert", "Search", "Remove", "Hits");
        out.println("-----------------------------------------------------------------------------------------------");

        for (TreeBenchmarkRow row : rows) {
            out.printf("%18s | %12s | %10d | %9d ms | %9d ms | %9d ms | %10d%n",
                    row.treeName(),
                    row.inputOrder(),
                    row.n(),
                    row.insertMs(),
                    row.searchMs(),
                    row.removeMs(),
                    row.searchHits());
        }

        out.println("===============================================================================================");
        out.println();
    }
}
