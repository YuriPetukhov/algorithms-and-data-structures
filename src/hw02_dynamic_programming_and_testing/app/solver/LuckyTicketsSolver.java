package hw02_dynamic_programming_and_testing.app.solver;

import java.math.BigInteger;
import java.util.Arrays;

public class LuckyTicketsSolver {

    public BigInteger solve(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");

        BigInteger[] dp = new BigInteger[9 * n + 1];
        Arrays.fill(dp, BigInteger.ZERO);
        dp[0] = BigInteger.ONE;

        for (int digits = 1; digits <= n; digits++) {
            int maxSum = 9 * digits;
            BigInteger[] next = new BigInteger[maxSum + 1];
            Arrays.fill(next, BigInteger.ZERO);

            BigInteger window = BigInteger.ZERO;

            for (int s = 0; s <= maxSum; s++) {
                if (s < dp.length) window = window.add(dp[s]);

                int removeIndex = s - 10;
                if (removeIndex >= 0 && removeIndex < dp.length) {
                    window = window.subtract(dp[removeIndex]);
                }

                next[s] = window;
            }
            dp = next;
        }

        BigInteger result = BigInteger.ZERO;
        for (BigInteger ways : dp) {
            result = result.add(ways.multiply(ways));
        }
        return result;
    }
}