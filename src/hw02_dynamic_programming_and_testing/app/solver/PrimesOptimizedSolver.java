package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.util.ArrayList;
import java.util.List;

public class PrimesOptimizedSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solve(Integer n) {
        if (n < 2) return 0;

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        for (int x = 3; x <= n; x += 2) {
            if (isPrimeUsingPrimes(x, primes)) primes.add(x);
        }

        return primes.size();
    }

    private static boolean isPrimeUsingPrimes(int x, List<Integer> primes) {
        for (int p : primes) {
            if ((long) p * p > x) break;
            if (x % p == 0) return false;
        }
        return true;
    }
}
