package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.Solver;
import hw02_dynamic_programming_and_testing.app.solver.PrimesNaiveSolver;

public class PrimesNaiveTask implements MeasurableTask<Integer, Integer> {

    private final Solver<Integer, Integer> solver = new PrimesNaiveSolver();

    @Override
    public String id() {
        return "primes_naive_count";
    }

    @Override
    public String displayName() {
        return "Простые числа: перебор делителей O(N^2)";
    }

    @Override
    public Integer parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input is null");

        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        int n;
        try {
            n = Integer.parseInt(t);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integer n, got: " + t);
        }

        if (n > 1_000_000) {
            throw new IllegalArgumentException("n is too large for naive recursion (try n <= 50)");
        }

        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        return n;

    }

    @Override
    public Integer compute(Integer n) {
        return solver.solve(n);
    }

    @Override
    public String format(Integer result) {
        return Integer.toString(result);
    }

    @Override
    public String run(String input) {
        return format(compute(parse(input)));
    }
}
