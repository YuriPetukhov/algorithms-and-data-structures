package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.app.solver.LuckyTicketsSolver;

import java.math.BigInteger;

public class LuckyTicketsTask implements Task {

    private final LuckyTicketsSolver solver = new LuckyTicketsSolver();

    @Override
    public String id() {
        return "lucky_tickets";
    }

    @Override
    public String displayName() {
        return "Счастливые билеты";
    }

    @Override
    public String run(String input) {
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

        BigInteger result = solver.solve(n);
        return result.toString();
    }
}
