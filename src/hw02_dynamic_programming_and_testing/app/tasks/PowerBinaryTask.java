package hw02_dynamic_programming_and_testing.app.tasks;

import hw02_dynamic_programming_and_testing.app.core.MeasurableTask;
import hw02_dynamic_programming_and_testing.app.core.PowerInput;
import hw02_dynamic_programming_and_testing.app.solver.PowerBinarySolver;
import hw02_dynamic_programming_and_testing.app.core.Solver;

import static hw02_dynamic_programming_and_testing.app.util.DoubleResultFormatter.formatResult;

public class PowerBinaryTask implements MeasurableTask<PowerInput, Double> {

    private final Solver<PowerInput, Double> solver = new PowerBinarySolver();

    @Override
    public String id() {
        return "power_binary";
    }

    @Override
    public String displayName() {
        return "Степень: двоичное разложение O(LogN)";
    }

    @Override
    public PowerInput parse(String input) {

        if (input == null) throw new IllegalArgumentException("Input is null");

        String t = input.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Empty input");

        String[] parts = t.split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Expected two tokens: <A> <N>. Example: 1.001 1000"
            );
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

        if (base <= 0) throw new IllegalArgumentException("base must be > 0");
        if (exp < 0) throw new IllegalArgumentException("exp must be >= 0");

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
