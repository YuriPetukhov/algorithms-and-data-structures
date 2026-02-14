package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

public class SieveBitsetOddSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solve(Integer n) {
        if (n < 2) return 0;
        if (n == 2) return 1;

        int oddCount = (n / 2) + 1;
        int[] bits = new int[(oddCount + 31) >>> 5];

        int limit = (int) Math.sqrt(n);

        for (int p = 3; p <= limit; p += 2) {
            int pi = p >>> 1;
            if (get(bits, pi)) continue;

            for (int x = p * p; x <= n; x += (p << 1)) {
                set(bits, x >>> 1);
            }
        }

        int count = 1;
        for (int x = 3; x <= n; x += 2) {
            if (!get(bits, x >>> 1)) count++;
        }
        return count;
    }

    private static boolean get(int[] bits, int i) {
        int word = i >>> 5;
        int bit = i & 31;
        return (bits[word] & (1 << bit)) != 0;
    }

    private static void set(int[] bits, int i) {
        int word = i >>> 5;
        int bit = i & 31;
        bits[word] |= (1 << bit);
    }
}
