package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.solver.FibGoldenRatioSolver;
import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.math.BigInteger;

public class FibGoldenRatioTask implements MeasurableTask<Long, BigInteger> {

    private final Solver<Long, BigInteger> solver = new FibGoldenRatioSolver();

    @Override public String id() { return "fib_golden_ratio"; }

    @Override
    public String displayName() {
        return "Фибоначчи: золотое сечение";
    }

    @Override
    public Long parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input is null");

        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        long n;
        try {
            n = Long.parseLong(t);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integer n, got: " + t);
        }

        if (n < 0) throw new IllegalArgumentException("n must be >= 0");

        return n;
    }

    @Override
    public BigInteger compute(Long n) {
        return solver.solve(n);
    }

    @Override
    public String format(BigInteger result) {
        return result.toString();
    }

    @Override
    public String run(String input) {
        return format(compute(parse(input)));
    }
}
