package hw10_search_trees.benchmark;

public record TreeBenchmarkRow(
        String treeId,
        String treeName,
        String inputOrder,
        int n,
        long insertMs,
        long searchMs,
        long removeMs,
        int searchHits
) {
}