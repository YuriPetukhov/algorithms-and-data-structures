package hw18_boyer_moore_algorithm.demo;

import hw18_boyer_moore_algorithm.benchmark.BenchmarkCase;
import hw18_boyer_moore_algorithm.benchmark.BenchmarkResult;
import hw18_boyer_moore_algorithm.benchmark.SearchBenchmark;
import hw18_boyer_moore_algorithm.libs.searching.BoyerMooreSearch;
import hw18_boyer_moore_algorithm.libs.searching.BruteForceSearch;
import hw18_boyer_moore_algorithm.libs.searching.PrefixShiftSearch;
import hw18_boyer_moore_algorithm.libs.searching.SubstringSearch;
import hw18_boyer_moore_algorithm.libs.searching.BadCharacterSearch;
import hw18_boyer_moore_algorithm.visualization.BenchmarkConsoleVisualizer;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkDemo {
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        List<SubstringSearch> algorithms = List.of(
                new BruteForceSearch(),
                new PrefixShiftSearch(),
                new BadCharacterSearch(),
                new BoyerMooreSearch()
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

        BenchmarkConsoleVisualizer.title("Substring search benchmark");
        BenchmarkConsoleVisualizer.results(results);
    }
}