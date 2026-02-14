package hw02_dynamic_programming_and_testing.app.solver;

import hw02_dynamic_programming_and_testing.app.core.PowerInput;
import hw02_dynamic_programming_and_testing.app.core.Solver;

public class PowerMultiplySolver implements Solver<PowerInput, Double> {

    @Override
    public Double solve(PowerInput input) {
        return powRec(input.base(), input.exp());
    }

    private static double powRec(double a, long n) {
        if (n == 0) return 1.0;

        double r = powRec(a, n >>> 1);
        double rr = r * r;
        return ((n & 1L) == 1L) ? (a * rr) : rr;
    }
}
