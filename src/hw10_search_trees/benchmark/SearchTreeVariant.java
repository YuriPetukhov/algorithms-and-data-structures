package hw10_search_trees.benchmark;

import hw10_search_trees.libs.searching.trees.IntSearchTree;

import java.util.function.Supplier;

public record SearchTreeVariant(
        String id,
        String displayName,
        Supplier<IntSearchTree> factory
) {
}