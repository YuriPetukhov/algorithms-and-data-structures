package hw11_optimal_search_trees.benchmark;

import hw11_optimal_search_trees.libs.searching.trees.optimal.OptimalSearchTree;
import hw11_optimal_search_trees.libs.searching.trees.optimal.OptimalSearchTreeBuilder;
import hw11_optimal_search_trees.libs.searching.trees.optimal.WeightedKey;

import java.util.List;

public final class OptimalSearchBenchmarkRunner {

    public OptimalSearchBenchmarkRow run(
            OptimalSearchTreeBuilder builder,
            List<WeightedKey> keys,
            int[] searchKeys
    ) {
        if (builder == null) {
            throw new IllegalArgumentException("builder is null");
        }
        if (keys == null) {
            throw new IllegalArgumentException("keys is null");
        }
        if (searchKeys == null) {
            throw new IllegalArgumentException("searchKeys is null");
        }

        long buildStart = System.currentTimeMillis();
        OptimalSearchTree tree = builder.build(keys);
        long buildMs = System.currentTimeMillis() - buildStart;

        int hits = 0;
        long searchStart = System.currentTimeMillis();
        for (int key : searchKeys) {
            if (tree.search(key)) {
                hits++;
            }
        }
        long searchMs = System.currentTimeMillis() - searchStart;

        return new OptimalSearchBenchmarkRow(
                builder.id(),
                builder.displayName(),
                keys.size(),
                buildMs,
                searchMs,
                hits
        );
    }
}