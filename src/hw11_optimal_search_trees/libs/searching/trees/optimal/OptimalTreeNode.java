package hw11_optimal_search_trees.libs.searching.trees.optimal;

public final class OptimalTreeNode {

    public final int key;
    public final long weight;

    public OptimalTreeNode left;
    public OptimalTreeNode right;

    public OptimalTreeNode(int key, long weight) {
        this.key = key;
        this.weight = weight;
    }
}