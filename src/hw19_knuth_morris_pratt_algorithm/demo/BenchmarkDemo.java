package hw19_knuth_morris_pratt_algorithm.demo;

import hw19_knuth_morris_pratt_algorithm.benchmark.BenchmarkCase;
import hw19_knuth_morris_pratt_algorithm.benchmark.BenchmarkResult;
import hw19_knuth_morris_pratt_algorithm.benchmark.SearchBenchmark;
import hw19_knuth_morris_pratt_algorithm.libs.searching.AutomatonSearch;
import hw19_knuth_morris_pratt_algorithm.libs.searching.KnuthMorrisPrattSearch;
import hw19_knuth_morris_pratt_algorithm.libs.searching.SubstringSearch;
import hw19_knuth_morris_pratt_algorithm.visualization.BenchmarkConsoleVisualizer;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkDemo {
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        List<SubstringSearch> algorithms = List.of(
                new AutomatonSearch(),
                new KnuthMorrisPrattSearch()
        );

        List<BenchmarkCase> cases = List.of(
                new BenchmarkCase(
                        "Pattern at start",
                        "ababdabcabcabcabcabcabcabcabc",
                        "ababd"
                ),
                new BenchmarkCase(
                        "Pattern in middle",
                        "abcabcabcabcababdabcabcabcabc",
                        "ababd"
                ),
                new BenchmarkCase(
                        "Pattern at end",
                        "abcabcabcabcabcabcabcabcababd",
                        "ababd"
                ),
                new BenchmarkCase(
                        "No match",
                        "abcabcabcabcabcabcabcabcabcabc",
                        "ababd"
                ),
                new BenchmarkCase(
                        "Many repeats",
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                        "aaab"
                )
        );

        SearchBenchmark benchmark = new SearchBenchmark();
        List<BenchmarkResult> results = new ArrayList<>();

        for (BenchmarkCase benchmarkCase : cases) {
            for (SubstringSearch algorithm : algorithms) {
                results.add(
                        benchmark.benchmark(
                                benchmarkCase,
                                algorithm,
                                ITERATIONS
                        )
                );
            }
        }

        BenchmarkConsoleVisualizer.title("KMP benchmark");
        BenchmarkConsoleVisualizer.results(results);

        BenchmarkConsoleVisualizer.emptyLine();
        BenchmarkConsoleVisualizer.conclusion();
    }
}