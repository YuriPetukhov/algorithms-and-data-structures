package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.Solver;
import hw02_dynamic_programming_and_testing.app.solver.SieveEratosthenesSolver;

public class SieveEratosthenesTask implements MeasurableTask<Integer, Integer> {

    private final Solver<Integer, Integer> solver = new SieveEratosthenesSolver();

    @Override public String id() { return "sieve_eratosthenes_count"; }

    @Override
    public String displayName() {
        return "Решето Эратосфена: O(N log log N)";
    }

    @Override
    public Integer parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input is null");
        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        int n;
        try { n = Integer.parseInt(t); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Expected integer n, got: " + t); }

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
