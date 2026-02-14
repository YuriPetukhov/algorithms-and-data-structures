package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.solver.LuckyTicketsSolver;
import hw02_dynamic_programming_and_testing.app.core.Solver;

import java.math.BigInteger;

public class LuckyTicketsTask implements MeasurableTask<Integer, BigInteger> {

    private final Solver<Integer, BigInteger> solver = new LuckyTicketsSolver();

    @Override
    public String id() {
        return "lucky_tickets";
    }

    @Override
    public String displayName() {
        return "Счастливые билеты";
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

        if (n < 0) throw new IllegalArgumentException("n must be >= 0");

        return n;
    }

    @Override
    public BigInteger compute(Integer n) {
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
