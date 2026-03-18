package hw11_optimal_search_trees.libs.searching.trees.optimal;

import java.util.List;

public interface OptimalSearchTreeBuilder {

    String id();

    String displayName();

    OptimalSearchTree build(List<WeightedKey> keys);

}