package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

public class SieveEratosthenesSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solve(Integer n) {
        if (n < 2) return 0;

        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) isPrime[i] = true;

        for (int p = 2; (long) p * p <= n; p++) {
            if (!isPrime[p]) continue;
            for (int m = p * p; m <= n; m += p) {
                isPrime[m] = false;
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) if (isPrime[i]) count++;
        return count;
    }
}
