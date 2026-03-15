package hw10_search_trees.benchmark;

import hw10_search_trees.libs.searching.trees.IntSearchTree;

public final class TreeBenchmarkRunner {

    public TreeBenchmarkRow run(
            SearchTreeVariant variant,
            String inputOrder,
            TreeBenchmarkData data
    ) {
        if (variant == null) {
            throw new IllegalArgumentException("variant is null");
        }
        if (inputOrder == null || inputOrder.isBlank()) {
            throw new IllegalArgumentException("inputOrder is blank");
        }
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }

        IntSearchTree tree = variant.factory().get();
        if (tree == null) {
            throw new IllegalStateException("SearchTree factory returned null for " + variant.id());
        }

        long insertStart = System.currentTimeMillis();
        for (int value : data.insertData()) {
            tree.insert(value);
        }
        long insertMs = System.currentTimeMillis() - insertStart;

        int searchHits = 0;
        long searchStart = System.currentTimeMillis();
        for (int value : data.searchData()) {
            if (tree.search(value)) {
                searchHits++;
            }
        }
        long searchMs = System.currentTimeMillis() - searchStart;

        long removeStart = System.currentTimeMillis();
        for (int value : data.removeData()) {
            tree.remove(value);
        }
        long removeMs = System.currentTimeMillis() - removeStart;

        return new TreeBenchmarkRow(
                variant.id(),
                variant.displayName(),
                inputOrder,
                data.insertData().length,
                insertMs,
                searchMs,
                removeMs,
                searchHits
        );
    }
}
