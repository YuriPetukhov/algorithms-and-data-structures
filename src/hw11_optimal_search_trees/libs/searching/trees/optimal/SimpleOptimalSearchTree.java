package hw11_optimal_search_trees.libs.searching.trees.optimal;

public final class SimpleOptimalSearchTree implements OptimalSearchTree {

    private final OptimalTreeNode root;

    public SimpleOptimalSearchTree(OptimalTreeNode root) {
        this.root = root;
    }

    @Override
    public boolean search(int key) {
        OptimalTreeNode node = root;

        while (node != null) {
            if (key == node.key) {
                return true;
            }

            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
    }
}