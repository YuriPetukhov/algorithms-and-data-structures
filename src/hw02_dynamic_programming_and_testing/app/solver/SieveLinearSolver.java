package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

public class SieveLinearSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solve(Integer n) {
        if (n < 2) return 0;
        if (n == 2) return 1;

        int size = (n - 1) / 2;
        int[] lp = new int[size];
        int[] primes = new int[estimatePrimeCount(n)];
        int pc = 0;

        primes[pc++] = 2;
        int count = 1;

        for (int i = 3; i <= n; i += 2) {
            int li = getLpOdd(lp, i);
            if (li == 0) {
                setLpOdd(lp, i, i);
                li = i;
                primes[pc++] = i;
                count++;
            }

            for (int j = 0; j < pc; j++) {
                int p = primes[j];
                long x = (long) p * i;
                if (x > n) break;

                if ((x & 1L) == 1L) {
                    setLpOdd(lp, (int) x, p);
                }

                if (p == li) break;
            }
        }

        return count;
    }

    private static int getLpOdd(int[] lp, int x) {
        return lp[(x - 3) >>> 1];
    }

    private static void setLpOdd(int[] lp, int x, int p) {
        lp[(x - 3) >>> 1] = p;
    }

    private static int estimatePrimeCount(int n) {
        if (n < 17) return n;
        return (int) (1.25506 * n / Math.log(n)) + 10;
    }
}
