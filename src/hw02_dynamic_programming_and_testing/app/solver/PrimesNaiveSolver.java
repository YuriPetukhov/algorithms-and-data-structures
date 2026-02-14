package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.Solver;

public class PrimesNaiveSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solve(Integer n) {
        if (n < 2) return 0;

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrimeNaive(i)) count++;
        }
        return count;
    }

    private static boolean isPrimeNaive(int x) {
        if (x < 2) return false;
        for (int d = 2; d <= x - 1; d++) {
            if (x % d == 0) return false;
        }
        return true;
    }
}
