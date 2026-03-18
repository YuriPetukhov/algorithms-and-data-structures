package hw11_optimal_search_trees.benchmark;

import java.io.PrintStream;
import java.util.List;

public final class OptimalSearchReportPrinter {

    private OptimalSearchReportPrinter() {
    }

    public static void print(List<OptimalSearchBenchmarkRow> rows, PrintStream out) {
        out.println();
        out.println("================ OPTIMAL SEARCH TREES BENCHMARK REPORT ================");
        out.printf("%28s | %10s | %12s | %12s | %10s%n",
                "Builder", "Size", "Build", "Search", "Hits");
        out.println("-----------------------------------------------------------------------");

        for (OptimalSearchBenchmarkRow row : rows) {
            out.printf("%28s | %10d | %9d ms | %9d ms | %10d%n",
                    row.builderName(),
                    row.size(),
                    row.buildMs(),
                    row.searchMs(),
                    row.searchHits());
        }

        out.println("=======================================================================");
        out.println();
    }
}