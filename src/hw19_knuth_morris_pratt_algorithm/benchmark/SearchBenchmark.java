package hw19_knuth_morris_pratt_algorithm.benchmark;

import hw19_knuth_morris_pratt_algorithm.libs.searching.SearchResult;
import hw19_knuth_morris_pratt_algorithm.libs.searching.SubstringSearch;

public class SearchBenchmark {
    public BenchmarkResult benchmark(
            BenchmarkCase benchmarkCase,
            SubstringSearch algorithm,
            int iterations
    ) {
        long totalTime = 0;
        SearchResult lastResult = null;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();

            lastResult = algorithm.search(
                    benchmarkCase.text(),
                    benchmarkCase.pattern()
            );

            long end = System.nanoTime();
            totalTime += end - start;
        }

        long averageTime = totalTime / iterations;

        return new BenchmarkResult(
                benchmarkCase.name(),
                algorithm.name(),
                benchmarkCase.text().length(),
                benchmarkCase.pattern().length(),
                iterations,
                averageTime,
                lastResult.comparisons(),
                lastResult.index()
        );
    }
}