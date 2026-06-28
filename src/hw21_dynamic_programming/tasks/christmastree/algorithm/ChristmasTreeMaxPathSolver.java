package hw21_dynamic_programming.tasks.christmastree.algorithm;

public final class ChristmasTreeMaxPathSolver {

    public int solve(int[][] tree) {
        int n = tree.length;
        int[][] dp = new int[n][];
        dp[0] = new int[]{tree[0][0]};

        for (int row = 1; row < n; row++) {
            dp[row] = new int[row + 1];
            for (int col = 0; col <= row; col++) {
                int bestParent = Integer.MIN_VALUE;
                if (col < row) {
                    bestParent = Math.max(bestParent, dp[row - 1][col]);
                }
                if (col > 0) {
                    bestParent = Math.max(bestParent, dp[row - 1][col - 1]);
                }
                dp[row][col] = bestParent + tree[row][col];
            }
        }

        int answer = dp[n - 1][0];
        for (int value : dp[n - 1]) {
            answer = Math.max(answer, value);
        }
        return answer;
    }
}
