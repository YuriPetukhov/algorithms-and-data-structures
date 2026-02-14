package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.PowerInput;
import hw02_dynamic_programming_and_testing.app.solver.PowerIterativeSolver;
import hw02_dynamic_programming_and_testing.app.core.Solver;

import static hw02_dynamic_programming_and_testing.app.util.DoubleResultFormatter.formatResult;

public class PowerIterativeTask implements MeasurableTask<PowerInput, Double> {

    private final Solver<PowerInput, Double> solver = new PowerIterativeSolver();

    private static final long MAX_EXP = 100_000_000_000L;

    @Override
    public String id() {
        return "power_iterative";
    }

    @Override
    public String displayName() {
        return "Степень: итеративный O(N)";
    }

    @Override
    public PowerInput parse(String input) {

        if (input == null) throw new IllegalArgumentException("Input is null");

        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        String[] parts = t.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Expected: <A> <N>");
        }

        double base;
        long exp;

        try {
            base = Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected decimal base, got: " + parts[0]);
        }

        try {
            exp = Long.parseLong(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected integer exp, got: " + parts[1]);
        }

        if (base <= 0.0)
            throw new IllegalArgumentException("base must be > 0");

        if (exp < 0)
            throw new IllegalArgumentException("exp must be >= 0");

        if (exp > MAX_EXP)
            throw new IllegalArgumentException(
                    "Exponent too large for iterative O(N) algorithm: " + exp
            );

        return new PowerInput(base, exp);
    }

    @Override
    public Double compute(PowerInput input) {
        double value = solver.solve(input);

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("result is not finite");
        }

        return value;
    }

    @Override
    public String format(Double result) {
        return formatResult(result);
    }

    @Override
    public String run(String input) {
        return format(compute(parse(input)));
    }
}
