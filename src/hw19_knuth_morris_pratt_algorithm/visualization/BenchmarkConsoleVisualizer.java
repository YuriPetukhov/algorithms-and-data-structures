package hw19_knuth_morris_pratt_algorithm.visualization;

import hw19_knuth_morris_pratt_algorithm.benchmark.BenchmarkResult;

import java.util.List;

public final class BenchmarkConsoleVisualizer {
    private BenchmarkConsoleVisualizer() {
    }

    public static void title(String title) {
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
    }

    public static void results(List<BenchmarkResult> results) {
        printHeader();

        for (BenchmarkResult result : results) {
            printRow(result);
        }
    }

    public static void conclusion() {
        System.out.println("Conclusion:");
        System.out.println("Finite automaton search preprocesses the pattern into a transition table.");
        System.out.println("Knuth-Morris-Pratt uses the prefix function to avoid redundant comparisons.");
        System.out.println("Both algorithms provide linear search over the text after preprocessing.");
        System.out.println("KMP usually requires less preprocessing memory than an explicit finite automaton.");
    }

    public static void emptyLine() {
        System.out.println();
    }

    private static void printHeader() {
        System.out.printf(
                "%-25s %-22s %10s %12s %12s %15s %12s %8s%n",
                "Case",
                "Algorithm",
                "Text len",
                "Pattern len",
                "Iterations",
                "Avg time ns",
                "Comparisons",
                "Index"
        );

        System.out.println("-".repeat(125));
    }

    private static void printRow(BenchmarkResult result) {
        System.out.printf(
                "%-25s %-22s %10d %12d %12d %15d %12d %8d%n",
                result.caseName(),
                result.algorithm(),
                result.textLength(),
                result.patternLength(),
                result.iterations(),
                result.averageTimeNs(),
                result.comparisons(),
                result.index()
        );
    }
}