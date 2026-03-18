package hw11_optimal_search_trees.libs.searching.trees.optimal.dp;

import hw11_optimal_search_trees.libs.searching.trees.optimal.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OptimalBSTAlgorithm1 implements OptimalSearchTreeBuilder {

    @Override
    public String id() {
        return "optimal_bst_dp";
    }

    @Override
    public String displayName() {
        return "Optimal BST DP";
    }

    @Override
    public OptimalSearchTree build(List<WeightedKey> keys) {
        if (keys == null) {
            throw new IllegalArgumentException("keys is null");
        }
        if (keys.isEmpty()) {
            return new SimpleOptimalSearchTree(null);
        }

        List<WeightedKey> sorted = new ArrayList<>(keys);
        sorted.sort(Comparator.comparingInt(WeightedKey::key));

        int n = sorted.size();

        long[][] cost = new long[n][n];
        int[][] root = new int[n][n];
        long[] prefix = buildPrefixSums(sorted);

        for (int i = 0; i < n; i++) {
            cost[i][i] = sorted.get(i).weight();
            root[i][i] = i;
        }

        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;

                long bestCost = Long.MAX_VALUE;
                int bestRoot = left;
                long sum = rangeSum(prefix, left, right);

                for (int r = left; r <= right; r++) {
                    long leftCost = (r > left) ? cost[left][r - 1] : 0L;
                    long rightCost = (r < right) ? cost[r + 1][right] : 0L;
                    long total = leftCost + rightCost + sum;

                    if (total < bestCost) {
                        bestCost = total;
                        bestRoot = r;
                    }
                }

                cost[left][right] = bestCost;
                root[left][right] = bestRoot;
            }
        }

        OptimalTreeNode treeRoot = buildTree(sorted, root, 0, n - 1);
        return new SimpleOptimalSearchTree(treeRoot);
    }

    private static long[] buildPrefixSums(List<WeightedKey> keys) {
        long[] prefix = new long[keys.size()];
        long sum = 0L;

        for (int i = 0; i < keys.size(); i++) {
            sum += keys.get(i).weight();
            prefix[i] = sum;
        }

        return prefix;
    }

    private static long rangeSum(long[] prefix, int left, int right) {
        if (left == 0) {
            return prefix[right];
        }
        return prefix[right] - prefix[left - 1];
    }

    private static OptimalTreeNode buildTree(
            List<WeightedKey> keys,
            int[][] root,
            int left,
            int right
    ) {
        if (left > right) {
            return null;
        }

        int r = root[left][right];
        WeightedKey wk = keys.get(r);

        OptimalTreeNode node = new OptimalTreeNode(wk.key(), wk.weight());
        node.left = buildTree(keys, root, left, r - 1);
        node.right = buildTree(keys, root, r + 1, right);
        return node;
    }
}