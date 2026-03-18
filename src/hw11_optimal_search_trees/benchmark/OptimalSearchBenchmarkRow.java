package hw11_optimal_search_trees.benchmark;

public record OptimalSearchBenchmarkRow(
        String builderId,
        String builderName,
        int size,
        long buildMs,
        long searchMs,
        int searchHits
) {
}